package com.meesig.model;

import lombok.Data;

@Data
public class ItemOptionRow {
	private int idx;
	private long price;
	private String description;

	public ItemOptionRow(){
	}
	
	public ItemOptionRow(int idx, long pri, String des) {
		this.idx = idx;
		this.price = pri;
		this.description = des;
	}

}
