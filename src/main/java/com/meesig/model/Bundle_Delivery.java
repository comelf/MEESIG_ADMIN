package com.meesig.model;

import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("bundel_delivery")
public class Bundle_Delivery {
	private int delivery_id;
	private long ORDER_bundle_id;
	private Date delivery_date;
	private int delivery_state;
	private String delivery_code;
	private String delivery_name;
	private String delivery_link;
	
	public Bundle_Delivery(){
		
	}
}
