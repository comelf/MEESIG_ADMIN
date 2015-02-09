package com.meesig.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.Item;
import com.meesig.model.ItemForList;
import com.meesig.model.User;
import com.meesig.service.ItemManager;
import com.meesig.util.Paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemManagementController.class);

    @Autowired
    ItemManager itemManager;
    
    @RequestMapping("/list")
     public String adminItemList(@RequestParam(defaultValue="1", required=false) int page, 
    		 					 @RequestParam(defaultValue="20", required=false) int count,
    		 					 @RequestParam(defaultValue="recent", required=false) String sort,
    		 					 Model model) {
    	if ( count>100)	{ count = 100; 	}
    	if ( count<10 )	{ count = 10; 	}
    	if ( page<1   )	{ page =1; 		}
    	
    	List<ItemForList> itemList = itemManager.selectSortedItemList(sort, count, page);
    	Paging paging = new Paging(page, itemManager.getTotalUser(), count);
    	model.addAttribute("itemList", itemList);
    	model.addAttribute("paging", paging);
        
        return "item/list";
    }
    
    @RequestMapping("/detail/{item_id}")
    public String userDetail(@PathVariable int item_id, Model model) {
		
    	model.addAttribute("user", itemManager.findItemByIdForManagement(item_id));
    	
    	return "item/detail";
    }

    @RequestMapping("/charge")
    public String adminItemcharge(Model model) {
        LOG.debug("로그인!!");

        Map<String, String> map = new HashMap<String, String>();
        map.put("items", "1");
        map.put("news","10");
        map.put("orders","1120");
        map.put("users","100/100000");

        model.addAttribute("dash", map);
        return "item/charge";
    }

    @RequestMapping("/category")
    public String adminItemCategory(Model model) {
        LOG.debug("로그인!!");

        Map<String, String> map = new HashMap<String, String>();
        map.put("items", "1");
        map.put("news","10");
        map.put("orders","1120");
        map.put("users","100/100000");

        model.addAttribute("dash", map);
        return "item/category";
    }

    @RequestMapping("/add")
    public String adminItemAdd(Model model) {
        LOG.debug("로그인!!");

        Map<String, String> map = new HashMap<String, String>();
        map.put("items", "1");
        map.put("news","10");
        map.put("orders","1120");
        map.put("users","100/100000");

        model.addAttribute("dash", map);
        return "item/add";
    }
}
