package com.meesig.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Alias("item")
public class Item {

	private int item_id;
	private int item_category_id;
	private int shops_shop_id;
	@Range(min = 1, max = 2147483647)
	private int media_media_id;
	private String media_photo_url;
	@NotEmpty
	private String item_name;
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z]{1}[A-Za-z0-9_-]{2,19}$")
	private String item_path_url;
	private String item_description;
	private Date write_date;

	private long item_sell_price;
	private long item_supply_price;
	private int item_sale_price;

	private int item_daily_stock;

	private String item_content;
	private String item_recommend;
	private String item_origin;
	private String item_shipping;

	@Range(min = 1, max = 10)
	private int item_shipping_day_state;
	@Range(min = 1, max = 10)
	private int item_shipping_price_state;
	@Range(min = 1, max = 10)
	private int item_option_state;
	@Range(min = 0, max = 10)
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

	public int getMedia_media_id() {
		return media_media_id;
	}

	public void setMedia_media_id(int media_media_id) {
		this.media_media_id = media_media_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
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

	public String getItem_shipping() {
		return item_shipping;
	}

	public void setItem_shipping(String item_shipping) {
		this.item_shipping = item_shipping;
	}

	public int getItem_sale_price() {
		return item_sale_price;
	}

	public void setItem_sale_price(int item_sale_price) {
		this.item_sale_price = item_sale_price;
	}

	public int getItem_daily_stock() {
		return item_daily_stock;
	}

	public void setItem_daily_stock(int item_daily_stock) {
		this.item_daily_stock = item_daily_stock;
	}

	public int getItem_option_state() {
		return item_option_state;
	}

	public void setItem_option_state(int item_option_state) {
		this.item_option_state = item_option_state;
	}

	public int getItem_shipping_day_state() {
		return item_shipping_day_state;
	}

	public void setItem_shipping_day_state(int item_shipping_day_state) {
		this.item_shipping_day_state = item_shipping_day_state;
	}

	public int getItem_shipping_price_state() {
		return item_shipping_price_state;
	}

	public void setItem_shipping_price_state(int item_shipping_price_state) {
		this.item_shipping_price_state = item_shipping_price_state;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public int getItem_state() {
		return item_state;
	}

	public void setItem_state(int item_state) {
		this.item_state = item_state;
	}

	public String getMedia_photo_url() {
		return media_photo_url;
	}

	public void setMedia_photo_url(String media_photo_url) {
		this.media_photo_url = media_photo_url;
	}

}
