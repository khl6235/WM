package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	public void init() throws ServletException{
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

//    public DispatcherServlet() {
//        super();
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//1. 클라이언트의 요청 path 정보를 추출.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		//2. HanderMapping을 통해 path에 해당하는 Controller 검색
		Controller ctrl = handlerMapping.getController(path);
		
		//3. 검색된 Controller 실행
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver를 통해 viewName에 해당하는 화면 검색
		String view = null;
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		}
		else {
			view = viewName;
		}
		
		//5. 검색된 화면으로 이동
		response.sendRedirect(view);
		
	}

}
