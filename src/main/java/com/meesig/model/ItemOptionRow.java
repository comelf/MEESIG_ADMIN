package com.meesig.model;

import lombok.Data;

@Data
public class ItemOptionRow {
	private int idx;
	private int price;
	private String description;

	public ItemOptionRow(){
	}
	
	public ItemOptionRow(int idx, int pri, String des) {
		this.idx = idx;
		this.price = Integer.valueOf(pri);
		this.description = des;
	}

}
