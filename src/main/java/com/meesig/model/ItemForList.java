package com.meesig.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemForList")
public class ItemForList {

	private int item_id;
	private String category_name;
	private String shop_name;
	private String item_name;
	private String media_file_name;
	private String media_file_extension;
	private String media_file_path;
	private int item_sell_price;
	private int item_option_state;
	private String location_name;
	private Date item_write_date;
	private String media_photo_url;
	
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

	public int getItem_sell_price() {
		return item_sell_price;
	}

	public void setItem_sell_price(int item_sell_price) {
		this.item_sell_price = item_sell_price;
	}

	public int getItem_option_state() {
		return item_option_state;
	}

	public void setItem_option_state(int item_option_state) {
		this.item_option_state = item_option_state;
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

	public String getMedia_file_name() {
		return media_file_name;
	}

	public void setMedia_file_name(String media_file_name) {
		this.media_file_name = media_file_name;
	}

	public String getMedia_file_extension() {
		return media_file_extension;
	}

	public void setMedia_file_extension(String media_file_extension) {
		this.media_file_extension = media_file_extension;
	}

	public String getMedia_file_path() {
		return media_file_path;
	}

	public void setMedia_file_path(String media_file_path) {
		this.media_file_path = media_file_path;
	}

	public String getMedia_photo_url() {
		return media_file_path+media_file_name+media_file_extension;
	}

	public void setMedia_photo_url(String media_photo_url) {
		this.media_photo_url = media_photo_url;
	}
	
}
