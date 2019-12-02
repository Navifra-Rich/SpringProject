package com.sp.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.ex.dto.postDTO;

@Service
public interface BoardService {
		public void createPost(postDTO dto);
		public List<postDTO> viewAll();
		public postDTO showPost(int idx);
}
