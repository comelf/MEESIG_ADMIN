package com.meesig.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("shippingPrice")
public class ShippingPrice {
	private int price_id;
	private int items_item_id;
	private List<PriceOption> price_options = new ArrayList<PriceOption>();

	public ShippingPrice() {

	}

	public int getPrice_id() {
		return price_id;
	}

	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}

	public int getItems_item_id() {
		return items_item_id;
	}

	public void setItems_item_id(int items_item_id) {
		this.items_item_id = items_item_id;
	}

	public void addPrice(int index, int price, String desc) {
		PriceOption po = new PriceOption(index,price, desc);
		this.price_options.add(po);

	}

	public List<PriceOption> getPrice_options() {
		return price_options;
	}

	public void setPrice_options(List<PriceOption> price_options) {
		this.price_options = price_options;
	}

	public void addPriceList(String[] pri, String[] des) {
		this.price_options.clear();
		int len = pri.length;
		
		for (int i = 0; i < len; i++) {
			addPrice(i, Integer.valueOf(pri[i]), des[i]);
		}
		
		
	}

}
