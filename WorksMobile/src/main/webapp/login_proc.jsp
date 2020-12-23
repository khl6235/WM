<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.intern.assignment.user.UserVO" %>
<%@ page import="com.intern.assignment.user.impl.UserDAO" %>

<%

String id = request.getParameter("id");
String password = request.getParameter("password");

UserVO vo = new UserVO();
vo.setId(id);
vo.setPassword(password);

UserDAO userDAO = new UserDAO();
UserVO user = userDAO.getUser(vo);

if(user != null){
	response.sendRedirect("getFormList.jsp");
}
else
	response.sendRedirect("login.jsp");
	
%>