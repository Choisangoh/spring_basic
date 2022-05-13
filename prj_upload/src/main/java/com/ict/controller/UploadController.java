package com.ict.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j // pom.xml에서 junit은 4.12, log4j는 1.2.17, exculsions 태그 삭제, scope 주석처리
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	@PostMapping("/uploadForm")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		for(MultipartFile multipartFile : uploadFile) {
			
			String uploadFolder = "C:\\upload_data\\temp";
			
			log.info("----------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		} // end for
	}
	
	
	
	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	@PostMapping("/uploadAjax")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("ajax post update !");
		
		String uploadFolder = "C:\\upload_data\\temp";
		
		for(MultipartFile multipartFile : uploadFile) {
					
		log.info("----------------");
		log.info("Upload File Name : " + multipartFile.getOriginalFilename());
		log.info("Upload File Size : " + multipartFile.getSize());
					
		String uploadFileName = multipartFile.getOriginalFilename();
		
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1); // 경로가 안 맞을 경우를 대비해서 적음, 없어도 돌아가긴 함
		
		log.info("last file name : " + uploadFileName);
		
		File saveFile = new File(uploadFolder, uploadFileName);
		
		try {
			multipartFile.transferTo(saveFile);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	  } // end for
	}
}