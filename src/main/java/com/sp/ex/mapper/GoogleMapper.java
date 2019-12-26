package com.sp.ex.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sp.ex.dto.GoogleAccountDTO;


public interface GoogleMapper {
	public GoogleAccountDTO getAccountInfo(String userID);
}
