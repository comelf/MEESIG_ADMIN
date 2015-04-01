package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("banner")
@Data
public class Banner {
	private int slide_id;
	private int slide_order;
	private String slide_src;
	private String slide_href;
	private String slide_des;
	
	public Banner(){
		
	}
}
