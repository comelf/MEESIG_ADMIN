package com.meesig.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemForList")
public class ItemForList {
	private int item_id;
	private String category_name;
	private String shop_name;
	private String item_name;
	private String item_img_filename;
	private String item_img_extension;
	private int item_sell_price;
	private int item_state;
	private String location_name;
	private Date item_write_date;
	
	public ItemForList() {
		
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_img_filename() {
		return item_img_filename;
	}

	public void setItem_img_filename(String item_img_filename) {
		this.item_img_filename = item_img_filename;
	}

	public int getItem_sell_price() {
		return item_sell_price;
	}

	public void setItem_sell_price(int item_sell_price) {
		this.item_sell_price = item_sell_price;
	}

	public int getItem_state() {
		return item_state;
	}

	public void setItem_state(int item_state) {
		this.item_state = item_state;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public Date getItem_write_date() {
		return item_write_date;
	}

	public void setItem_write_date(Date item_write_date) {
		this.item_write_date = item_write_date;
	}

	public String getItem_img_extension() {
		return item_img_extension;
	}

	public void setItem_img_extension(String item_img_extension) {
		this.item_img_extension = item_img_extension;
	}
	
	
}
