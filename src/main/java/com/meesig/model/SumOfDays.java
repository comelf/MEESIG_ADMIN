package com.meesig.model;

import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("sumOfDays")
public class SumOfDays {
	private Date reserve_date;
	private int sum;
	
	public SumOfDays(){
		
	}
}
