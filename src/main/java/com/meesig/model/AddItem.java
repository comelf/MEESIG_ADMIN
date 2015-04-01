package com.meesig.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.meesig.options.Option;

@EqualsAndHashCode(callSuper=true)
@Data
public class AddItem extends Item {
	List<Shop> shopList;
	List<Category> categoryList;

	List<Option> shippingDaySelectList;
	ShippingDayManage sdm;

	List<Option> shippingPriceSelectList;
	ShippingPriceManage spm;

	List<Option> itemOptionSelectList;
	ItemOptionManage iom;

	List<Option> itemStateList;

	public AddItem() {
		super();
	}


	public String validationOfShippingDayState() {
		super.getItem_shipping_day_state();
		return sdm.validation();
	}

	public String validationOfShippingPriceState() {
		return spm.validation();
	}

	public String validationOfItemOptionState() {
		return iom.validation();
	}

}
