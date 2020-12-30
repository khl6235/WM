<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri= "http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>설문 목록</title>
</head>
<body>
<center>

<h1>설문 목록</h1>
<h3>${userName }님 환영합니다...<a href="logout.do">Log-out</a></h3>


<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">번호</th>
	<th bgcolor="orange" width="250">제목</th>
	<th bgcolor="orange" width="150">작성자</th>
	<th bgcolor="orange" width="200">등록 시간</th>
</tr>

<c:forEach items="${formList }" var="form">
<tr>
	<td>${form.formIdx }</td>
	<td align="left"><a href="getForm.do?formIdx=${form.formIdx }">${form.title }</a></td>
	<td>${form.userIdx }</td>
	<td>${form.createdAt }</td>
</tr>
</c:forEach>
</table>
<br>
<a href="insertForm.jsp">새 설문 등록</a>

</center>

</body>
</html>