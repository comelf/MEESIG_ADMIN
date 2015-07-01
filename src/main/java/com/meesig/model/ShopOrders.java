package com.meesig.model;

import java.util.List;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("shopOrders")
public class ShopOrders {
	private int shop_id;
	private String shop_name;
	private int shop_state;
	private List<SumOfDays> sumOfDays;
	
	public ShopOrders(){
		
	}
}
