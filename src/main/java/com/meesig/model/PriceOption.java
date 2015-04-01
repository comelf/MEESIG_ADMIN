package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("priceOption")
@Data
public class PriceOption {
	private int index;
	private int price;
	private String desc;

	public PriceOption(int index, int price, String desc) {
		this.index = index;
		this.price = price;
		this.desc = desc;
	}

}
