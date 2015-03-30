package com.meesig.model;

import java.util.List;

import com.meesig.options.Option;

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

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Option> getShippingDaySelectList() {
		return shippingDaySelectList;
	}

	public void setShippingDaySelectList(List<Option> shippingDaySelectList) {
		this.shippingDaySelectList = shippingDaySelectList;
	}

	public ShippingDayManage getSdm() {
		return sdm;
	}

	public void setSdm(ShippingDayManage sdm) {
		this.sdm = sdm;
	}

	public List<Option> getShippingPriceSelectList() {
		return shippingPriceSelectList;
	}

	public void setShippingPriceSelectList(List<Option> shippingPriceSelectList) {
		this.shippingPriceSelectList = shippingPriceSelectList;
	}

	public ShippingPriceManage getSpm() {
		return spm;
	}

	public void setSpm(ShippingPriceManage spm) {
		this.spm = spm;
	}

	public List<Option> getItemOptionSelectList() {
		return itemOptionSelectList;
	}

	public void setItemOptionSelectList(List<Option> itemOptionSelectList) {
		this.itemOptionSelectList = itemOptionSelectList;
	}

	public ItemOptionManage getIom() {
		return iom;
	}

	public void setIom(ItemOptionManage iom) {
		this.iom = iom;
	}

	public List<Option> getItemStateList() {
		return itemStateList;
	}

	public void setItemStateList(List<Option> itemStateList) {
		this.itemStateList = itemStateList;
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
