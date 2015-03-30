package com.meesig.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meesig.options.Option;

@Service
public class OptionMaker {
	
	public List<Option> makeShippingDaySelectList(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("1","당일배송"));
		itemOptionState.add(new Option("2","익일배송"));
		itemOptionState.add(new Option("3","배송일지정"));
		return itemOptionState;
	}
	
	public List<Option> makeShippingPriceSelectList(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("1","무료"));
		itemOptionState.add(new Option("2","단일가"));
		itemOptionState.add(new Option("3","선택적"));
		return itemOptionState;
	}
	
	public List<Option> makeItemOptionSelectList(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("1","옵션 없음"));
		itemOptionState.add(new Option("2","옵션 있음"));
		return itemOptionState;
	}
	
	public List<Option> makeItemStateList() {
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("1","판매종료"));
		itemOptionState.add(new Option("2","판매대기"));
		itemOptionState.add(new Option("3","판매상품"));
		itemOptionState.add(new Option("4","세일상품"));
		return itemOptionState;
	}



}
