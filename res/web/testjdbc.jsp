<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<html>
	<head>
		<title>Test Sample</title>
	</head>
	<body>
		<h1>answer from jsp</h1>
		<h1>"<c:out value="out by jstl core"/>"</h1>
		<table>
		<tr><td><c:out value="before native java"/></td></tr>
		<%
			List<String> stringList = (List<String>)request.getAttribute("strings");
			for(String str : stringList) {
				%><tr><td><c:out value="founded string"/></td></tr><%
			}
			request.setAttribute("strings", stringList);
			pageContext.setAttribute("pagestrings", stringList);
		%>
		<tr><td><c:out value="after native java"/></td></tr>

		<c:forEach var="item" items="pageScope.pagestrings">
			<tr><td>string</td></tr>
		</c:forEach>
		<c:forEach var="item" items="${requestScope.groups}">
			<tr><td>group</td></tr>
		</c:forEach>
		<c:forEach var="item" items="${pageScope.pagestrings}">
			<tr><td>string</td></tr>
		</c:forEach>

		<c:forEach var="item" items="${requestScope.groups}">
			<tr><td>group</td></tr>
		</c:forEach>

		<c:forEach var="item" items="pagestrings">
			<tr><td>string</td></tr>
		</c:forEach>
		<c:forEach var="item" items="${requestScope.groups}">
			<tr><td>group</td></tr>
		</c:forEach>
		<c:forEach var="item" items="${pagestrings}">
			<tr><td>string</td></tr>
		</c:forEach>

		<tr><td><c:out value="before native java"/></td></tr>
		<%
			stringList = (List<String>)request.getAttribute("strings");
			for(String str : stringList) {
				%><tr><td><c:out value="founded string"/></td></tr><%
			}
		%>
		<tr><td><c:out value="after native java"/></td></tr>

		</table>
	</body>
</html>