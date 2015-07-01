package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("menuOrderInfo")
public class MenuOrderInfo {
	private int shop_id;
	private int item_id;
	private String shop_name;
	private String item_name;
	private int sum_state_process;
	private int sum_state_shop;
	private int sum_state_delivery;
	
	public MenuOrderInfo(){
		
	}
	
}
