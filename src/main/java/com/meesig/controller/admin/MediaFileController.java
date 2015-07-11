package com.meesig.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.meesig.model.UploadResult;
import com.meesig.service.FileManager;

@Controller
@RequestMapping("/media")
public class MediaFileController {
	private static final Logger LOG = LoggerFactory
			.getLogger(MediaFileController.class);

	@Autowired
	FileManager fileManager;

	@RequestMapping(value="/photoUpload", method=RequestMethod.POST)
	@ResponseBody
	public UploadResult photoFileUploader(@RequestParam("file")MultipartFile file, @RequestParam("des") String des,
			HttpServletRequest request) {
//		//로컬용
//		String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/images");
//		UploadResult result = fileManager.readAndSave(file,des,savePath);
		
//		//서버용
		UploadResult result = fileManager.readAndSave(file, des);
		
		return result;
		
	}

	@RequestMapping(value="/originPhotoUpload", method=RequestMethod.POST)
	@ResponseBody
	public UploadResult originPhotoFileUploader(@RequestParam("file")MultipartFile file, @RequestParam("des") String des,
			HttpServletRequest request) {
		UploadResult result = fileManager.saveOne(file, des);		
		return result;
		
	}
	
	@RequestMapping(value="/menuPhotoUpload", method=RequestMethod.POST)
	@ResponseBody
	public UploadResult menuPhotoFileUploader(@RequestParam("file")MultipartFile file, @RequestParam("alt") String des,
			HttpServletRequest request) {
		UploadResult result = fileManager.saveOne(file, des);
		return result;
		
	}
	
//	private String getSavePath(HttpServletRequest request){
//		return request.getSession().getServletContext().getRealPath("/WEB-INF/images");
//	}
	
	
}
