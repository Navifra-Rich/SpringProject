package com.sp.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/File")
public class FileController {
	@RequestMapping("/fileDownload")
	public void fileDownload(
			@Param("path")String path,
			HttpServletResponse response
			) throws Exception {
		System.out.println("path = "+path);
		
		response.setHeader("Content-Disposition","attachment;filename="+path+";");
		response.setContentType("text/plain");
		File file=new File(path);
		FileInputStream fileIn = new FileInputStream(file);
		response.flushBuffer();
	}
}
