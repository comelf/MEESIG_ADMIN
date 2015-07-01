package com.meesig.model;

import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("itemForList")
@Data
public class ItemForList {

	private int item_id;
	private String category_name;
	private String shop_name;
	private String item_name;
	private String item_description;
	private String media_file_name;
	private String media_file_extension;
	private String media_file_path;
	private int media_id;
	private int item_sell_price;
	private int item_state;
	private String location_name;
	private Date item_write_date;
	public ItemForList() {

	}

	public String getMedia_photo_url() {
		return media_file_path+media_file_name+"2."+media_file_extension;
	}

}
