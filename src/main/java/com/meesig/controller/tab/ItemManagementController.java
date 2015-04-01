package com.meesig.controller.tab;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.AddItem;
import com.meesig.model.Category;
import com.meesig.model.ItemForList;
import com.meesig.model.Shop;
import com.meesig.service.ItemDBManager;
import com.meesig.service.ItemService;
import com.meesig.util.Paging;

@Controller
@RequestMapping("/item")
public class ItemManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemManagementController.class);

    @Autowired
    ItemDBManager itemDBManager;
    
    @Autowired
    ItemService itemService;
    
    @RequestMapping("/list")
     public String adminItemList(@RequestParam(defaultValue="1", required=false) int page, 
    		 					 @RequestParam(defaultValue="20", required=false) int count,
    		 					 @RequestParam(defaultValue="recent", required=false) String sort,
    		 					 Model model) {
    	if ( count>100)	{ count = 100; 	}
    	if ( count<10 )	{ count = 10; 	}
    	if ( page<1   )	{ page =1; 		}
    	
    	List<ItemForList> itemList = itemDBManager.selectSortedItemList(sort, count, page);
    	Paging paging = new Paging(page, itemDBManager.getTotalUser(), count);
    	model.addAttribute("itemList", itemList);
    	model.addAttribute("paging", paging);
        
        return "item/list";
    }
    
    @RequestMapping(value="/list/search", method=RequestMethod.GET)
    public String itemListSearch(Model model, @RequestParam String query, 
    										  @RequestParam(defaultValue="name") String type) {
    	
    	if(query.equals("")){
    		return "redirect:/item/list";
    	}
    	
    	List<ItemForList> itemList = itemDBManager.searchItmeList(type, query);
    	model.addAttribute("itemList", itemList);
    	
    	if(itemList.size()>=100){
    		model.addAttribute("msg", "검색결과가 너무 많습니다. 전부 출력되지 않을 수 있습니다.");
    	}
    	
        return "item/list";
    }
    
    
    
    
    @RequestMapping("/detail/{item_id}")
    public String itemDetail(@PathVariable int item_id, Model model) {
    	AddItem addItem = itemService.makeNewItem();
    	addItem.setCategoryList(getCategoryList());
    	addItem.setShopList(getShopList());
    	
    	//addItem.setItemFromDB(itemDBManager.findItemByIdForManagement(item_id));
    	model.addAttribute("addItem", addItem);
    	
    	
    	return "item/add";
    }
//
//	@RequestMapping(value="/edit", method=RequestMethod.POST)
//    public String editItem(Item item, Model model){
//		if(itemManager.updateItemInfo(item)){
//			model.addAttribute("msg", "상품 정보를 변경하였습니다.");
//		}else{
//			model.addAttribute("msg", "상품 정보 변경실패.");
//		}
//    	
//        model.addAttribute("category", getCategoryList());
//        model.addAttribute("shops", getShopList());
//    	model.addAttribute("item", itemManager.findItemByIdForManagement(item.getItem_id()));
//		
//		return "item/detail";
//    }
    
    @RequestMapping("/add")
    public String itemAddInAdmin(Model model) {
    	AddItem addItem = itemService.makeNewItem();
    	addItem.setCategoryList(getCategoryList());
    	addItem.setShopList(getShopList());
    	
        model.addAttribute("addItem", addItem);
        return "item/add";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String createItem(@Valid AddItem addItem, BindingResult bindingResult, Model model) {
    	addItem.setCategoryList(getCategoryList());
    	addItem.setShopList(getShopList());
    	itemService.reloadItem(addItem);
    	
        if(bindingResult.hasErrors()){
        	model.addAttribute("msg", "상품 추가 오류");
			return "item/add";
        }
        
        String msg = itemDBManager.isValidItemStates(addItem);
        
        if(msg!=null){
        	model.addAttribute("msg", msg);
			return "item/add";
        }
        
        if(itemDBManager.isExistItemId(addItem)){
        	model.addAttribute("msg", "상품의 아이디는 고유해야 합니다.");
    		return  "item/add";
        }
        
    	if(!itemDBManager.createItmeInAdminPage(addItem)){
    		model.addAttribute("msg", "상품 추가 실패");
    		return  "item/add";
    	}
    	
    	addItem = itemService.makeNewItem();
    	addItem.setCategoryList(getCategoryList());
    	addItem.setShopList(getShopList());
    	
    	model.addAttribute("msg", "상품이 추가되었습니다.");
        return "item/add";
    }
    
    
    
    /*
     * 카테고리 관리
     */
    @RequestMapping("/category")
    public String adminItemCategory(@RequestParam(defaultValue="0",required=false) int category_id,Model model) {
        model.addAttribute("categoryList", getCategoryList());
        
        Category category = new Category();
        if(category_id>0){
        	category = itemDBManager.selectCategory(category_id);
        }
        model.addAttribute("category", category);
        return "item/category";
    }
    
    @RequestMapping(value="/category/add", method=RequestMethod.POST)
    public String addCategory(Category category, Model model) {
    	itemDBManager.insertCategory(category);
        return "redirect:/item/category";
    }
    
    @RequestMapping(value="/category/edit", method=RequestMethod.POST)
    public String editCategory(Category category, Model model) {
    	itemDBManager.updateCategory(category);
        return "redirect:/item/category";
    }
    
    @RequestMapping(value="/category/delete")
    public String deleteCategory(@RequestParam int category_id,
    							Category category, Model model) {
    	
    	try{
    		itemDBManager.deleteCategory(category_id);
    	}catch (Exception e){
        	model.addAttribute("msg", "삭제할수 없습니다. (해당 분류의 상품이 존재합니다.)");
        	model.addAttribute("categoryList", getCategoryList());
        	model.addAttribute("category", new Category());
        	return "item/category"; 
    	}
    	
        return "redirect:/item/category";
    }
    
    /*
     * 배송비 관리
     */
    @RequestMapping("/charge")
    public String adminItemcharge(Model model) {

        return "item/charge";
    }    
    
    /*
     * 중복코드 줄이기
     */
    private List<Shop> getShopList() {
		return itemDBManager.selecShopList();
	}
	private List<Category> getCategoryList() {
		return itemDBManager.selectCategoryList();
	}
}
