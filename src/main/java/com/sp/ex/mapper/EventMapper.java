package com.sp.ex.mapper;

import org.apache.ibatis.annotations.Param;

public interface EventMapper {
	public void attendEvent(@Param("postID")String postID, @Param("userID")String userID);
}
