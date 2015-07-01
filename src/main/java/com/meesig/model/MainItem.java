package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("recommend")
public class MainItem {
	private int row_id;
	private int main_key;
	private int main_order;
	private int items_item_id;
	private int media_media_id;
	private String main_text;
	private String main_sub;
	
	private Media media;
	
	public MainItem(){
		
	}
	
}
