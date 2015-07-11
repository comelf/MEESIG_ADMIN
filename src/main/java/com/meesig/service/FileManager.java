package com.meesig.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meesig.model.BundleDelivery;
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

	@Autowired
	Environment env;
	
	public UploadResult readAndSave(MultipartFile file, String des) {
		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des, env.getRequiredProperty("meesig.file.path"), env.getRequiredProperty("meesig.hostname"));
		
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
		PhotoInfo pi = new PhotoInfo(file.getOriginalFilename(), file.getSize(), des, env.getRequiredProperty("meesig.file.path"), env.getRequiredProperty("meesig.hostname"));
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

	public File save(MultipartFile file) throws IllegalStateException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new java.util.Date());
		String fileName = today +"_" + file.getOriginalFilename();
		File eFile = new File(env.getRequiredProperty("meesig.file.path")+"/excel/"+fileName);
		file.transferTo(eFile);
		return eFile;
	}

	public List<BundleDelivery> readExcelFile(File eFile) throws BiffException, IOException {
		Workbook book = Workbook.getWorkbook(eFile);
		Sheet sheet = book.getSheet(0);
		int rowCount = sheet.getRows();
		List<BundleDelivery> bdl = new ArrayList<BundleDelivery>();
		
		for ( int rowIndex = 1; rowIndex < rowCount; rowIndex++){
			BundleDelivery bd = new BundleDelivery();
			Cell c1 = sheet.getCell(11, rowIndex);
			Cell c2 = sheet.getCell(12, rowIndex);
			Cell c3 = sheet.getCell(13, rowIndex);
			
			long bId = Long.valueOf(c1.getContents());
			String bName = c2.getContents();
			String bCode = c3.getContents();
			
			if(bId<0 || bName.isEmpty() || bCode.isEmpty()){
				continue;
			}
			
			bd.setORDER_bundle_id(bId);
			bd.setDelivery_name(bName);
			bd.setDelivery_code(bCode);
			bd.setDelivery_state(1);
			bd.setDelivery_link("");
			
			bdl.add(bd);
		}
		
		return bdl;
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
