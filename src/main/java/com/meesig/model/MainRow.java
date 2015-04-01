package com.meesig.model;

import java.text.NumberFormat;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("mainRow")
@Data
public class MainRow {

	private int main_key;
	private int main_order;
	private String main_link_url;
	private String main_text;
	private String main_sub;

	private String item_name;
	private String item_path_url;
	private String item_description;
	private long item_sell_price;
	private long item_sale_price;

	private int item_option_state;
	private int item_new_menu_state;
	private int item_shipping_day_state;

	private String media_file_path;
	private String media_file_name;
	private String media_file_extension;

	private NumberFormat numberFormatter = NumberFormat.getInstance();
	
	public MainRow() {

	}

	
	public String getReview() {
		if(main_text.length()<40){
			return main_text;
		}else{
			return main_text.substring(0, 37) + "...";
		}
	}
	

	public String getSpotImgUrl() {
		return this.media_file_path + this.media_file_name+ 0 +"."
				+ this.media_file_extension;
	}
	
	public String getRecommendImgUrl(){
		return this.media_file_path + this.media_file_name + 3 + "."
				+ this.media_file_extension;
	}
	
	public String getReviewImgUrl() {
		return this.media_file_path + this.media_file_name + 2 + "."
				+ this.media_file_extension;
	}
	
	public String getMenuImgUrl() {
		return this.media_file_path + this.media_file_name + 1 + "."
				+ this.media_file_extension;
	}
	
	public String getConvertedSellPirce(){
		return numberFormatter.format(item_sell_price) + "원"; 
	}
	
	public String getConvertedSalePirce(){
		return numberFormatter.format(item_sale_price) + "원"; 
	}
}
	