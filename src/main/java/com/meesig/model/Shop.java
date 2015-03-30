package com.meesig.model;

import org.apache.ibatis.type.Alias;

@Alias("shop")
public class Shop {

	private int shop_id;
	private int locations_location_id;
	private String shop_name;
	private String shop_discription;
	private String shop_content;
	private String shop_location_detail;
	private int shop_state;
	private int media_media_id;

	public Shop() {

	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public int getLocations_location_id() {
		return locations_location_id;
	}

	public void setLocations_location_id(int locations_location_id) {
		this.locations_location_id = locations_location_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_discription() {
		return shop_discription;
	}

	public void setShop_discription(String shop_discription) {
		this.shop_discription = shop_discription;
	}

	public String getShop_content() {
		return shop_content;
	}

	public void setShop_content(String shop_content) {
		this.shop_content = shop_content;
	}

	public String getShop_location_detail() {
		return shop_location_detail;
	}

	public void setShop_location_detail(String shop_location_detail) {
		this.shop_location_detail = shop_location_detail;
	}

	public int getShop_state() {
		return shop_state;
	}

	public void setShop_state(int shop_state) {
		this.shop_state = shop_state;
	}

	public int getMedia_media_id() {
		return media_media_id;
	}

	public void setMedia_media_id(int media_media_id) {
		this.media_media_id = media_media_id;
	}

}
