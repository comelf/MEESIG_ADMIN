package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

import com.meesig.util.ConvertUtil;

@Alias("orderMenu")
@Data
public class OrderMenu {
	private long om_id;
	private int items_item_id;
	private long order_bundle_id;
	
	private int om_review_state;
	private int om_item_count;
	private int om_shipping_price;
	private long om_item_price;
	private long om_total_price;
	private String om_reserve_delivery_day;
	private String om_option;
	private Boolean om_isBundle;
	private int om_charge_shipping_price;
	
	private String menuImg;
	private String item_name;
	
	private String media_file_path;
	private String media_file_name;
	private String media_file_extension;
	
	public OrderMenu(){
		
	}
	
	public String getConvertedSellPirce(){
		return ConvertUtil.currency(om_item_price); 
	}
	
	public String getConvertedShippingPrice(){
		return ConvertUtil.currency(om_shipping_price);
	}
	
	public String getConvertedMultiplyPrice(){
		return ConvertUtil.currency(om_item_price * om_item_count);
	}
	
	public String getConvertedTotalPrice(){
		return ConvertUtil.currency(om_total_price);
	}
	
	public String getConvertedChargeShippingPrice(){
		return ConvertUtil.currency(om_charge_shipping_price);
	}
	
	public String getMenuImg(){
		if(menuImg ==null){
			return media_file_path + media_file_name+ "4."+ media_file_extension;
		}
		return menuImg;
	}
	
}
