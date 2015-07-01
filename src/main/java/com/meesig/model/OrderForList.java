package com.meesig.model;

import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

import com.meesig.support.state.OrderState;

@Data
@Alias("orderForList")
public class OrderForList {
	private int order_id;
	private Date order_date;
	private String order_name;
	private long order_payment_price;
	private int order_state;
	private String user_login_id;
	private String user_name;
	private String user_phone;
	
	public OrderForList(){
		
	}
	
	public String getConvertOrderState(){
		return OrderState.getStateString(order_state);
	}
	
}
