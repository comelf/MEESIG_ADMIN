package com.meesig.model;

import java.util.List;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("itemOption")
@Data
public class ItemOptionManage {
	private int items_item_id;
	List<OptionBlock> optionBlocks;

	public ItemOptionManage(){
		
	}

	public String validation() {
		return null;
	}

}
