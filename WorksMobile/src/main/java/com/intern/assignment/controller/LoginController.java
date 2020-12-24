package com.intern.assignment.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intern.assignment.user.UserService;
import com.intern.assignment.user.UserVO;
import com.intern.assignment.user.impl.UserDAO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    BasicDataSource dataSource;
	
	@RequestMapping("/login.do")
	public String usercheck(UserVO vo){
		System.out.println(userService.getUser(vo));
		vo.setId("test");
		vo.setPassword("test123");
		return "login";
		/*
		 * Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select now() as now;");
            
            while(rs.next()) {
                model.addAttribute("serverTime", rs.getString("now"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();    
            
        } finally {
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }                        
        }
		 */
	}
	
	/*
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		System.out.println("로그인 화면으로 이동");
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		if(vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		UserVO user = userDAO.getUser(vo);
		
		if(userDAO.getUser(vo) != null){
			session.setAttribute("userName", user.getId());
			return "getBoardList.do";
		}
		else{
			return "login.jsp";
		}
		
	}*/

}
