package com.meesig.controller.tab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meesig.model.MenuOrderInfo;
import com.meesig.model.OrderBundle;
import com.meesig.model.OrderForList;
import com.meesig.model.Orders;
import com.meesig.model.Shop;
import com.meesig.model.ShopOrders;
import com.meesig.service.ItemService;
import com.meesig.service.OrderService;
import com.meesig.util.Paging;

@Controller
@RequestMapping("/order")
public class OrderManagementController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderManagementController.class);

    @Autowired
    OrderService orderService;
    
    @Autowired
    ItemService itemService;
    
    @RequestMapping("/list")
     public String adminOrderList(@RequestParam(defaultValue="1", required=false) int page, 
    		 					 @RequestParam(defaultValue="20", required=false) int count,
    		 					 @RequestParam(defaultValue="recent", required=false) String sort,
    		 					 Model model) {
    	if ( count>100)	{ count = 100; 	}
    	if ( count<10 )	{ count = 10; 	}
    	if ( page<1   )	{ page =1; 		}
    	
    	List<OrderForList> orderList = orderService.selectSortedOrderList(sort, count, page);
    	Paging paging = new Paging(page, orderService.getTotalOrder(), count);
    	model.addAttribute("orderList", orderList);
    	model.addAttribute("paging", paging);
        
        return "order/list";
    }
    
    @RequestMapping(value="/list/search", method=RequestMethod.GET)
    public String itemListSearch(Model model, @RequestParam String query, 
    										  @RequestParam(defaultValue="name") String type) {
    	
    	if(query.equals("")){
    		return "redirect:/order/list";
    	}
    	
    	List<OrderForList> orderList = orderService.searchOrderList(type, query);
    	model.addAttribute("orderList", orderList);
    	
    	if(orderList.size()>=100){
    		model.addAttribute("msg", "검색결과가 너무 많습니다. 전부 출력되지 않을 수 있습니다.");
    	}
    	
        return "order/list";
    }
    
    @RequestMapping("/detail/{order_id}")
    public String itemDetail(@PathVariable long order_id, Model model) {
    	Orders order = orderService.selectOrderByOrderId(order_id);
    	model.addAttribute("order", order);
    	return "order/detail";
    }
    
    @RequestMapping("/orders")
    public String adminOrderListForOrder(Model model) {
	    List<MenuOrderInfo> menuOrders = orderService.selectOrderListByShops();
	   	model.addAttribute("menuOrders", menuOrders);
       return "order/orders";
    }
    
    @RequestMapping("/output")
	public String adminOrderOutput(Model model) {
    	List<ShopOrders> shopOrders = orderService.selectAllShopItems();
    	List<Date> outDays = orderService.getOutDays();
    	model.addAttribute("outDays", outDays);
    	model.addAttribute("shopOrders", shopOrders);
    	return "order/output";
    }
    
    @RequestMapping("/update/process")
    public String adminOrderUpdateProcess(@RequestParam("shop")int shopId,
    									  @RequestParam("date")String date, Model model) {
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
			Date tDate = sdf.parse(date);
    		orderService.updateOrderStateProcess(shopId, tDate);
    		model.addAttribute("msg", "해당 주문의 상태를 조리중으로 변경했습니다.");
    	}catch (Exception e){
    		model.addAttribute("msg", "주문 상태 변경에 실패했습니다.");
    	}
    	
    	List<ShopOrders> shopOrders = orderService.selectAllShopItems();
    	List<Date> outDays = orderService.getOutDays();
    	model.addAttribute("outDays", outDays);
    	model.addAttribute("shopOrders", shopOrders);
    	return "order/output";
    }
    
	@RequestMapping("/excel")
	public ModelAndView excel(@RequestParam("shop")int shopId,
			  				  @RequestParam("date")String date, Model model) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		Date tDate = sdf.parse(date);
		
		List<OrderBundle> bundleList =  orderService.selectOrderBundleWithDelivery(shopId, tDate);
		if (shopId == 2) {
			return new ModelAndView("excelFileForJoongang", "bundleList", bundleList);
		} else {
			return new ModelAndView("excelFileForAll", "bundleList", bundleList);
		}
	}
    
}
