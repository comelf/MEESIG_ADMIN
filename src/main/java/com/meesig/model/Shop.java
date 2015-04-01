package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("shop")
@Data
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

}
