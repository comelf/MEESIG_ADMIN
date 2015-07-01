package com.meesig.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meesig.model.ItemForList;
import com.meesig.service.ItemDBManager;
import com.meesig.service.ItemService;
import com.meesig.util.Paging;

@Controller
public class PopupController {
	private static final Logger LOG = LoggerFactory
			.getLogger(PopupController.class);

    @Autowired
    ItemDBManager itemDBManager;
    
    @Autowired
    ItemService itemService;
	
	@RequestMapping("/popup/item")
	public String popupItemList(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "20", required = false) int count,
			@RequestParam(defaultValue = "recent", required = false) String sort,
			Model model) {
		if ( count>100)	{ count = 100; 	}
		if ( count<10 )	{ count = 10; 	}
		if ( page<1   )	{ page =1; 		}
		
		List<ItemForList> itemList = itemDBManager.selectSortedItemList(sort,
				count, page);
		Paging paging = new Paging(page, itemDBManager.getTotalItem(), count);
		model.addAttribute("itemList", itemList);
		model.addAttribute("paging", paging);

		return "popup/itemlist";
	}

}
