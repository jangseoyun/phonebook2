<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>

<%
	//컨트롤러가 보내준 데이터 받아오기(강제 형변환을 해줘야함 원래는 오브젝트에있는것을 받아오기때문에 )
	//ex)int면 Integer로 받아옴-> 기본자료형
	//request의 setAttribute를 받아오는 것 
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");
%>

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
	
	<% 
	for(int i =0; i<personList.size(); i++){
	%>

		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td><%=personList.get(i).getName() %></td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td><%=personList.get(i).getHp() %></td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td><%=personList.get(i).getCompany() %></td>
			</tr>
		</table>
		<br>
		
	<% 
	} 
	%>
	
	
	
</body>
</html>