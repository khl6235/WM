package com.intern.assignment.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intern.assignment.user.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession mybatis;
	
	public UserVO getUser(UserVO vo) {
		return (UserVO) mybatis.selectOne("UserDAO.getUser", vo);
	}
	
	public void signUp(UserVO vo) {
		mybatis.insert("UserDAO.signUp", vo);
//		mybatis.commit();
	}

}