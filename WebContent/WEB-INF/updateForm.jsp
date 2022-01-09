<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.PersonVo" %>

<%
	PersonVo personVo = (PersonVo)request.getAttribute("personVo");
%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>pb2.updateForm</title>
</head>

<body>

	<h1>[phonebook2]</h1>
	<h2>전화번호 수정화면</h2>

	<p>수정 화면 입니다. 아래 항목을 수정하고 "수정" 버튼을 클릭하세요</p>

	<form action="/phonebook2/pbc" method="get">
		이름(name): <input type="text" name="name" value="<%= personVo.getName()%>"> <br>
		핸드폰(hp): <input type="text" name="hp" value="<%= personVo.getHp()%>"> <br>
		회사(company): <input type="text" name="company" value="<%= personVo.getCompany()%>"> <br>
		코드(id): <input type="text" name="id" value="<%= personVo.getPersonId()%>"> <br>
		<input type="text" name="action" value="update">
		<button type="submit">수정</button>
	</form>

</body>

</html>