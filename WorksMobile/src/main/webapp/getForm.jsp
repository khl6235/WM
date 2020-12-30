<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>설문 상세</title>
</head>
<body>

<center>
<h1>설문 상세</h1>
<a href="logout_proc.jsp">Log-out</a>
<hr>
<form action="updateForm.do" method="post">
<input name="formIdx" type="hidden" value="${form.formIdx }"/>
<table border="1" cellpadding="0" cellspacing="0">

<tr>
	<td bgcolor="orange" width="70">제목</td>
	<td align="left"><input name="title" type="text" value="${form.title }"/></td>
</tr>
<tr>
	<td bgcolor="orange" width="70">작성자</td>
	<td align="left">${form.userIdx }</td>
</tr>
<tr>
	<td bgcolor="orange" width="70">내용</td>
	<td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
</tr>
<tr>
	<td bgcolor="orange" width="70">등록일</td>
	<td align="left">${form.createdAt }</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="설문 수정"/>
	</td>
</tr>

</table>
</form>
<hr>
<a href="insertForm.jsp">설문 등록</a>&nbsp;&nbsp;&nbsp;
<a href="deleteForm.do?formIdx=${form.formIdx }">설문 삭제</a>&nbsp;&nbsp;&nbsp;
<a href="getFormList.do">설문 목록</a>
</center>
</body>
</html>