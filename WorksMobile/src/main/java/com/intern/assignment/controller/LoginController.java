package com.intern.assignment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intern.assignment.user.UserService;
import com.intern.assignment.user.UserVO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		
		System.out.println("로그인 화면으로 이동");
		
		vo.setId("test");
		vo.setPassword("test123");
		
		return "login.jsp";
	}
	
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		
		System.out.println("로그인 인증 처리...");
		
		UserVO user = userService.getUser(vo);
		if(user != null) {
			session.setAttribute("userName", user.getId());
			return "getFormList.do";
		}
			
		else {
			System.out.println("아이디와 비밀번호를 다시 확인하십시오.");
			return "login.jsp";
		}
	}
	
	@RequestMapping(value="/signup.do", method=RequestMethod.POST)
	public String signUp(UserVO vo) {
		System.out.println("회원가입 처리...");
		
		userService.signUp(vo);
		return "login.jsp";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login.jsp";
	}

}
