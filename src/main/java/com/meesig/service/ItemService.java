package com.meesig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meesig.model.AddItem;
import com.meesig.support.state.ItemState;

@Service
public class ItemService {
	@Autowired
	OptionMaker optionMaker;
	
	public AddItem makeNewItem() {
		AddItem item = new AddItem();
    	item.setItem_state(ItemState.WAIT_FOR_SALE);
    	item.setShippingDaySelectList(optionMaker.makeShippingDaySelectList());
    	item.setShippingPriceSelectList(optionMaker.makeShippingPriceSelectList());
    	item.setItemOptionSelectList(optionMaker.makeItemHasOption());
    	item.setItemState(optionMaker.makeItemStateList());
    	item.setIsSale(optionMaker.makeItemIsSale());
    	item.setIsSell(optionMaker.makeItemIsSell());
    	item.setIsNew(optionMaker.makeItemIsNew());
    	
		return item;
	}

	public void reloadItem(AddItem addedItem) {
		addedItem.setShippingDaySelectList(optionMaker.makeShippingDaySelectList());
		addedItem.setShippingPriceSelectList(optionMaker.makeShippingPriceSelectList());
		addedItem.setItemOptionSelectList(optionMaker.makeItemHasOption());
		addedItem.setItemState(optionMaker.makeItemStateList());
		addedItem.setIsSale(optionMaker.makeItemIsSale());
		addedItem.setIsSell(optionMaker.makeItemIsSell());
		addedItem.setIsNew(optionMaker.makeItemIsNew());
	}

}
