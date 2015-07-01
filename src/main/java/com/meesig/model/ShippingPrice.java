package com.meesig.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("shippingPrice")
@Data
public class ShippingPrice {
	private int price_id;
	private int items_item_id;
	private List<PriceOption> price_options = new ArrayList<PriceOption>();

	public ShippingPrice() {

	}

	public void addPrice(int index, int price, String desc) {
		
		PriceOption po = new PriceOption(index,price, desc);
		this.price_options.add(po);

	}

	public void addPriceList(String[] pri, String[] des) {
		this.price_options.clear();
		int len = pri.length;
		
		for (int i = 0; i < len; i++) {
			if(pri[i].isEmpty()||des[i].isEmpty()){
				continue;
			}
			addPrice(i, Integer.valueOf(pri[i]), des[i]);
		}
		
		
	}

}
