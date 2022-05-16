package com.ict.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j // pom.xml에서 junit은 4.12, log4j는 1.2.17, exculsions 태그 삭제, scope 주석처리
public class UploadController {
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());		
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();			
		}
		return false;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	@PostMapping("/uploadForm")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		// 어떤 폴더에 저장할건지 위치 지정
		String uploadFolder = "C:\\upload_data\\temp";
		
		// 폴더 생성 로직
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("----------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			// uuid 발급 부분
			UUID uuid = UUID.randomUUID();			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			// File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			// File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				// 썸네일 생성 로직
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				
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
		
		// 어떤 폴더에 저장할건지 위치 지정
		String uploadFolder = "C:\\upload_data\\temp";
		
		// 폴더 생성 로직
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multipartFile : uploadFile) {					
		log.info("----------------");
		log.info("Upload File Name : " + multipartFile.getOriginalFilename());
		log.info("Upload File Size : " + multipartFile.getSize());
		
		String uploadFileName = multipartFile.getOriginalFilename();
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1); // 경로가 안 맞을 경우를 대비해서 적음, 없어도 돌아가긴 함\
		log.info("last file name : " + uploadFileName);
		
		// uuid 발급 부분
		UUID uuid = UUID.randomUUID();		
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		// File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		// File saveFile = new File(uploadPath, uploadFileName);
				
		try {
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			
			// 썸네일 생성 로직
			if(checkImageType(saveFile)) {
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				thumbnail.close();
			}
			
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	  } // end for
   }
}