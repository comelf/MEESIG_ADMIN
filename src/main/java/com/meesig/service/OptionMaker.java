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
	
	public List<Option> makeItemHasOption(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("0","옵션 없음"));
		itemOptionState.add(new Option("1","옵션 있음"));
		return itemOptionState;
	}
	
	public List<Option> makeItemIsSale(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("0","세일 안함"));
		itemOptionState.add(new Option("1","세일 함"));
		return itemOptionState;
	}
	
	public List<Option> makeItemIsSell(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("0","노출 안함"));
		itemOptionState.add(new Option("1","노출 함"));
		return itemOptionState;
	}
	
	public List<Option> makeItemIsNew(){
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("0","일반"));
		itemOptionState.add(new Option("1","신상품"));
		return itemOptionState;
	}
	
	public List<Option> makeItemStateList() {
		List<Option> itemOptionState = new ArrayList<Option>();
		itemOptionState.add(new Option("1","판매종료"));
		itemOptionState.add(new Option("2","판매대기"));
		itemOptionState.add(new Option("3","판매상품"));
		return itemOptionState;
	}



}
