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
	</body>
</html>