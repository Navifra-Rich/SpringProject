package com.sp.ex;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.ex.service.FileService;

@Controller
@RequestMapping("/File")
public class FileController {
	
	@Autowired
	FileService fileService;
	@RequestMapping("/fileDownload")
	public void fileDownload(@Param("path") String path, HttpServletResponse response) throws Exception {
		
		System.out.println("path = " + path);
		response.setHeader("Content-Disposition", "attachment;filename=" + path + ";");
		response.setContentType("text/plain");
		File file = new File(path);
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		byte b[] = new byte[1024];
		int data = 0;
		while ((data = (fileIn.read(b, 0, b.length))) != -1) {
			servletOutputStream.write(b, 0, data);
		}

		response.flushBuffer();
		fileIn.close();
	}
	@RequestMapping(value="/displayImage")
	public void displayImage(@RequestParam("postID")String postID, HttpServletResponse res) throws Exception{
		System.out.println("postID= "+postID);
		fileService.displayImage(postID, res);
	}
}
