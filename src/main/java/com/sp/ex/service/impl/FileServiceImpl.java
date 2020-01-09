package com.sp.ex.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.ex.dto.FileDTO;
import com.sp.ex.mapper.BoardMapper;
import com.sp.ex.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	private String file_path = "C://Users//leesanghyeon//Desktop//abcd";
	private String image_path = "C://Users//leesanghyeon//Desktop//abcd//image";
	@Autowired
	BoardMapper boardMapper;

	@Override
	public void UplodeFileBoard(MultipartFile file, String postID, boolean isImage) {
		if (!file.isEmpty()) {
			List<FileDTO> fileDTO = new ArrayList<FileDTO>();
			String originalFileName = file.getOriginalFilename();

			String path;
			if (isImage)
				path = image_path + "//" + originalFileName;
			else
				path = file_path + "//" + originalFileName;
			try {
				fileDTO.add(new FileDTO(postID, path, originalFileName));
				file.transferTo(new File(path));
				if (isImage)
					writeImagePath(fileDTO);
				else
					writeFilePath(fileDTO);

			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeFilePath(List<FileDTO> fileDTO) {
		for (int i = 0; i < fileDTO.size(); i++) {
			boardMapper.writeFilePath(fileDTO.get(i));
		}

	}

	@Override
	public void writeImagePath(List<FileDTO> fileDTO) {
		for (int i = 0; i < fileDTO.size(); i++) {
			boardMapper.writeImagePath(fileDTO.get(i));
		}

	}

	@Override
	public void displayImage(String postID, HttpServletResponse res) throws Exception {

		FileDTO fileName = boardMapper.getImage(postID);
		if (fileName!=null) {
		FileInputStream in = null;
		BufferedOutputStream out = null;
		File file = new File(fileName.getDirectory());
			try {
				// String formatName= fileName.substring(fileName.lastIndexOf(".")+1);

				res.setHeader("Contents-disposition", "inline; fileName=" + fileName.getName());
				// res.setContentType("image/"+formatName);

				in = new FileInputStream(file);
				out = new BufferedOutputStream(res.getOutputStream());
				byte b[] = new byte[1024];
				while ((in.read(b)) != -1) {
					out.write(b);
				}

			} catch (Exception e) {

			} finally {
				in.close();
				out.flush();
				out.close();
			}
		}
	}
}
