package com.intern.assignment.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class UserServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. run Spring container
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. lookup FormServiceImpl object from container
		UserService userService = (UserService) container.getBean("userService");
		
		//3. test login
		UserVO vo = new UserVO();
		vo.setId("testtest");
		vo.setPassword("0000");
		
		UserVO user = userService.getUser(vo);
		if(user != null)
			System.out.println(user.getId()+"님 환영합니다.");
		else
			System.out.println("로그인 실패");
		
		//4. terminate
		container.close();

	}

}
