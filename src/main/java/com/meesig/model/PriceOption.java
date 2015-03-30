package com.meesig.model;

import org.apache.ibatis.type.Alias;

@Alias("priceOption")
public class PriceOption {
	private int index;
	private int price;
	private String desc;

	public PriceOption(int index, int price, String desc) {
		this.index = index;
		this.price = price;
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
