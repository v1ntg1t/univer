<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/hello.tld" prefix="jstlpg" %>
<html>
	<head>
		<title>Test Sample</title>
	</head>
	<body>
		<%
			String name = request.getParameter("name");
			if(name == null || name.length() == 0) {
				%>test without name<%
			} else {
				%>test with name '<%=name%>'<%
			}
		%>
		<br/>
		Test from custom tag library:
		<jstlpg:hello name='<%=request.getParameter("name")%>'/>
	</body>
</html>