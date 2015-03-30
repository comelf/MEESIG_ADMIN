package com.meesig.model;

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

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
