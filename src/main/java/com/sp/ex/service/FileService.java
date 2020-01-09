package com.sp.ex.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.ex.dto.FileDTO;

@Service
public interface FileService {

	void writeFilePath(List<FileDTO> fileDTO);


	void UplodeFileBoard(MultipartFile file, String postID, boolean isImage);


	void writeImagePath(List<FileDTO> fileDTO);


	void displayImage(String fileName, HttpServletResponse res) throws Exception;
	


}
