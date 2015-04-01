package com.meesig.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.Data;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Alias("item")
@Data
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

}
