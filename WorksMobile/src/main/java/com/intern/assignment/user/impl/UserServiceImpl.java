package com.intern.assignment.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.assignment.user.UserService;
import com.intern.assignment.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
//	public void setUserDAO(UserDAO userDAO) {
//		this.userDAO = userDAO;
//	}
	
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
	
	public void signUp(UserVO vo) {
		userDAO.signUp(vo);
	}
}
