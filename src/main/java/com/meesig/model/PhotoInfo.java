package com.meesig.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class PhotoInfo {
	private static final String DOT = ".";
	private int media_id;
	private String oriFileName;
	private String fileExt;
	private String savePath;
	private String uuFileName;
	private String fullPath;
	private String uploadPath = "/home/media";
	private long fileSize;
	private String requestPath = "http://localhost:8080/images/";

	public PhotoInfo(String fileName, long size, String des) {
		// 원본파일명
		this.oriFileName = fileName;
		// 파일확장자
		this.fileExt = getFileExt(fileName);
		// 저장경로(서버)
		this.savePath = getFilePath();
		// 파일명 UUID화
		this.uuFileName = getRealFileName();
		// 저장경로 + 파일명(uuid)
		this.fullPath = savePath + uuFileName + DOT + fileExt;
		this.fileSize = size;
	}

	private String getRealFileName() {
		String result = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new java.util.Date());
		result = today + UUID.randomUUID().toString();
		return result;
	}

	private String getFilePath() {
		String path = uploadPath + File.separator;
		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return path;
	}

	private String getFileExt(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		return ext.toLowerCase();
	}

	public String getOriFileName() {
		return oriFileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public String getSavePath() {
		return savePath;
	}

	public String getUuFileName() {
		return uuFileName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String path) {
		this.uploadPath = path;
		this.savePath = getFilePath();
		this.fullPath = savePath + uuFileName +DOT+ fileExt;
	}

	public void setFileSize(long size) {
		this.fileSize = size;
	}

	public long getFileSize() {
		return fileSize;
	}
	
	public String getRequestPath() {
		return requestPath;
	}

	public String getFileUrl() {
		return requestPath + uuFileName + DOT + fileExt;
	}

	public int getMedia_id() {
		return media_id;
	}

	public void setMedia_id(int media_id) {
		this.media_id = media_id;
	}

	public String getFullPathWithInt(int num) {
		return savePath + uuFileName + num + DOT + fileExt;
	}

	public String getFileUrlWithQuality(int i) {
		
		return requestPath + uuFileName +i + DOT + fileExt;
	}

}
