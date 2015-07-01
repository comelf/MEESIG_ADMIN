package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("media")
public class Media {

	private int media_id;
	private String media_file_name;
	private String media_file_extension;
	private String media_file_path;
	private String media_origin_name;
	private String media_upload_date;
	private String media_type;
	private String media_type_detail;
	private String media_file_des;

	public Media() {

	}

	public String getOriginImg() {
		return media_file_path + media_file_name + "0." + media_file_extension;
	}

	public String getSumnailImg() {
		return media_file_path + media_file_name + "2." + media_file_extension;
	}
}
