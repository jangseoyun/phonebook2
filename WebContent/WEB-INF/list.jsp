<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>pb2.listjsp</title>
</head>

<body>
	
	<h1>[phonebook2]</h1>
	
	<h2>전화번호 리스트</h2>
	
	<p>입력한 정보 내역입니다.</p>
	
	<c:forEach items="${requestScope.pList}" var="pList">
		<form action="/phonebook2/pbc" method="get">
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td>${pList.name}</td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${pList.hp}</td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${pList.company}</td>
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=updateForm&id=${pList.personId}">수정</a></td>
				<td><a href="/phonebook2/pbc?action=delete&id=${pList.personId}">삭제</a></td>
			</tr>
		</table>
		<br>
		</form>
	</c:forEach>
	
	
</body>
</html>