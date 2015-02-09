package com.meesig.model;

import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

@Alias("item")
public class Item {
	
	private int item_id; 
	private int item_category_id;
	private int shops_shop_id;
	@NotEmpty
	private String item_name;
	@NotEmpty	@Pattern(regexp = "^[A-Za-z]{1}[A-Za-z0-9]{4,20}$")
	private String item_path_url;
	private String item_img_url;
	private String item_discription;
	private long item_sell_price;
	private long item_supply_price;
	private String item_content;
	private String item_recommend;
	private String item_origin;
	private String item_delivery;
	private int item_sale_price;
	private int itme_daily_stock;
	private int item_state;
	
	public Item() {
		
	}
	
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_category_id() {
		return item_category_id;
	}
	public void setItem_category_id(int item_category_id) {
		this.item_category_id = item_category_id;
	}
	public int getShops_shop_id() {
		return shops_shop_id;
	}
	public void setShops_shop_id(int shops_shop_id) {
		this.shops_shop_id = shops_shop_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_path_url() {
		return item_path_url;
	}
	public void setItem_path_url(String item_path_url) {
		this.item_path_url = item_path_url;
	}
	public String getItem_img_url() {
		return item_img_url;
	}
	public void setItem_img_url(String item_img_url) {
		this.item_img_url = item_img_url;
	}
	public String getItem_discription() {
		return item_discription;
	}
	public void setItem_discription(String item_discription) {
		this.item_discription = item_discription;
	}
	public long getItem_sell_price() {
		return item_sell_price;
	}
	public void setItem_sell_price(long item_sell_price) {
		this.item_sell_price = item_sell_price;
	}
	public long getItem_supply_price() {
		return item_supply_price;
	}
	public void setItem_supply_price(long item_supply_price) {
		this.item_supply_price = item_supply_price;
	}
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	public String getItem_recommend() {
		return item_recommend;
	}
	public void setItem_recommend(String item_recommend) {
		this.item_recommend = item_recommend;
	}
	public String getItem_origin() {
		return item_origin;
	}
	public void setItem_origin(String item_origin) {
		this.item_origin = item_origin;
	}
	public String getItem_delivery() {
		return item_delivery;
	}
	public void setItem_delivery(String item_delivery) {
		this.item_delivery = item_delivery;
	}
	public int getItem_sale_price() {
		return item_sale_price;
	}
	public void setItem_sale_price(int item_sale_price) {
		this.item_sale_price = item_sale_price;
	}
	public int getItme_daily_stock() {
		return itme_daily_stock;
	}
	public void setItme_daily_stock(int itme_daily_stock) {
		this.itme_daily_stock = itme_daily_stock;
	}
	public int getItem_state() {
		return item_state;
	}
	public void setItem_state(int item_state) {
		this.item_state = item_state;
	}
	
	
	
}
