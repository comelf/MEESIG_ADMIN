package com.meesig.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("itemOption")
public class ItemOptionManage {
	private int items_item_id;
	List<OptionBlock> optionBlocks;

	public ItemOptionManage(){
		
	}
	
	public int getItems_item_id() {
		return items_item_id;
	}

	public void setItems_item_id(int items_item_id) {
		this.items_item_id = items_item_id;
	}

	public List<OptionBlock> getOptionBlocks() {
		return optionBlocks;
	}

	public void setOptionBlocks(List<OptionBlock> optionBlocks) {
		this.optionBlocks = optionBlocks;
	}

	public String validation() {
		return null;
	}

}
