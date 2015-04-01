package com.meesig.model;

import lombok.Data;

@Data
public class ShippingPriceManage {

	private int priceOpt2;
	private String[] priceOpt3Des;
	private String[] priceOpt3Pri;
	
	public ShippingPriceManage(){
		
	}

	public String validation() {
		return null;
	}
}
