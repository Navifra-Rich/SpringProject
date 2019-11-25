package com.sp.ex;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.sp.ex.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;

@Component
public class MemberDAO {
	
	@Autowired
	private SqlSession session;

	public void memberInsert(MemberDTO member) {
		 session.insert("com.sp.ex.mapper.MemberMapper.insertMember",member);

	}
	
}