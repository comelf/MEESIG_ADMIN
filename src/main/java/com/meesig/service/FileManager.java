package com.meesig.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meesig.model.PhotoInfo;
import com.meesig.model.UploadResult;
import com.meesig.util.ImageTransferUtil;

@Service
public class FileManager {
	
	private static int ERROR_IMAGE_ID = -1;
	private static final Logger LOG = LoggerFactory
			.getLogger(FileManager.class);

	@Autowired
	MediaDBManager mediaDBManager;	

	public UploadResult readAndSave(MultipartFile file, String des) {
		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des);
		
		ImageTransferUtil itu = new ImageTransferUtil();
		
		try {
			itu.save(file, pi);
			insertDatabase(pi);
		} catch (IllegalStateException | IOException e){
			itu.delete();
			LOG.warn("File transfer Error  : {}", e.getMessage());
			return resultError("파일을 서버에 저장할수 없습니다.");
		} catch (Exception e){
			itu.delete();
			LOG.warn("File meta data insert(DB) Error  : {}", e.getMessage());
			return resultError("파일을 데이터베이스에 저장할수 없습니다.");
		}
		
		return new UploadResult(false,pi.getUrlPathWithQuality(3), pi.getMedia_id());
		
	}
	
	public UploadResult saveOne(MultipartFile file, String des) {
		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des);
		ImageTransferUtil itu = new ImageTransferUtil();
		
		try {
			itu.saveOne(file, pi);
			insertDatabase(pi);
		} catch (IllegalStateException | IOException e){
			itu.delete();
			LOG.warn("File transfer Error  : {}", e.getMessage());
			return resultError("파일을 서버에 저장할수 없습니다.");
		} catch (Exception e){
			itu.delete();
			LOG.warn("File meta data insert(DB) Error  : {}", e.getMessage());
			return resultError("파일을 데이터베이스에 저장할수 없습니다.");
		}
		
		return new UploadResult(false,pi.getUrlPathWithQuality(0), pi.getMedia_id());
	}
	
	private void insertDatabase(PhotoInfo pi) {
		
		mediaDBManager.insertFileInfo(pi);
	}

	private UploadResult resultError(String msg) {
		return new UploadResult(true, msg, ERROR_IMAGE_ID );
	}
	
//	/*
//	 * 로컬용
//	 */
//	public UploadResult readAndSave(MultipartFile file, String des, String path) {
//		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des);
//		pi.setUploadPath(path);
//		
//		ImageTransferUtil itu = new ImageTransferUtil();
//		
//		try {
//			itu.save(file, pi);
//			insertDatabase(pi);
//		} catch (IllegalStateException | IOException e){
//			itu.delete();
//			LOG.warn("File transfer Error  : {}", e.getMessage());
//			return resultError("파일을 서버에 저장할수 없습니다.");
//		} catch (Exception e){
//			itu.delete();
//			LOG.warn("File meta data insert(DB) Error  : {}", e.getMessage());
//			return resultError("파일을 데이터베이스에 저장할수 없습니다.");
//		}
//		
//		return new UploadResult(false,pi.getFileUrlWithQuality(4), pi.getMedia_id());
//		
//	}
//
//	public UploadResult saveOne(MultipartFile file, String des, String path) {
//		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des);
//		pi.setUploadPath(path);
//		ImageTransferUtil itu = new ImageTransferUtil();
//		
//		try {
//			itu.saveOne(file, pi);
//			insertDatabase(pi);
//		} catch (IllegalStateException | IOException e){
//			itu.delete();
//			LOG.warn("File transfer Error  : {}", e.getMessage());
//			return resultError("파일을 서버에 저장할수 없습니다.");
//		} catch (Exception e){
//			itu.delete();
//			LOG.warn("File meta data insert(DB) Error  : {}", e.getMessage());
//			return resultError("파일을 데이터베이스에 저장할수 없습니다.");
//		}
//		
//		return new UploadResult(false,pi.getFileUrlWithQuality(0), pi.getMedia_id());
//	}



}
