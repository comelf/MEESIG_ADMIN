package com.meesig.model;

public class UploadResult {
	
	Boolean error;
	String msg;
	String image;
	String img_id;
	
	public UploadResult(boolean b, String m,int imgId) {
		this.error = b;
		this.img_id = String.valueOf(imgId);
		//파일업로드가 안되었을때
		if(b){
			this.msg = m;
			
		//파일업로드가 되었을때
		}else{
			this.image = m;
		}
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getImg_id() {
		return img_id;
	}

	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	
}
