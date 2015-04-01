package com.meesig.model;

import lombok.Data;

@Data
public class ShippingDayManage {

	private String[] dayOpt1;
	private String dayOpt1time;
	private String[] dayOpt2;
	private String dayOpt2time;
	private String dayOpt3;

	public ShippingDayManage() {

	}

	public String validation() {

		return null;
	}

}
