<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Test Sample</title>
	</head>
	<body>
		answer from jsp: "<c:out value="out by jstl core"/>"
		<table border=1>
			<c:forEach var="item" items="${groups}">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.curator}</td>
					<td>${item.speciality}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>