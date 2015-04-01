package com.meesig.model;

import java.util.List;

import lombok.Data;

@Data
public class OptionBlock {
	private int option_id;
	private String optionTitle;
	private List<ItemOptionRow> optionRow;

	public OptionBlock() {
	}
}
