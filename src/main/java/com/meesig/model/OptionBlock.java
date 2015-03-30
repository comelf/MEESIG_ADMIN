package com.meesig.model;

import java.util.List;

public class OptionBlock {
	private int option_id;
	private String optionTitle;
	private List<ItemOptionRow> optionRow;

	public OptionBlock() {
	}
	
	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public String getOptionTitle() {
		return optionTitle;
	}

	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}

	public List<ItemOptionRow> getOptionRow() {
		return optionRow;
	}

	public void setOptionRow(List<ItemOptionRow> optionRow) {
		this.optionRow = optionRow;
	}

}
