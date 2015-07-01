package com.meesig.model;

import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("delivery")
public class OrderDelivery {

	private int delivery_id;
	private String delivery_name;
	private String delivery_phone;
	private String delivery_pcode;
	private String delivery_addr1;
	private String delivery_addr2;
	private String delivery_des;
	private Date delivery_date;
	private long orders_order_id;
	
	public OrderDelivery(){
		
	}
}
