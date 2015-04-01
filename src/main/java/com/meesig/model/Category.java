package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("category")
@Data
public class Category {
	private int category_id;
	private String category_name;
	
	public Category() {
	}
}
