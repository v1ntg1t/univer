<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Список студентов</title>
	</head>
	<body>
		<form action='<c:url value="/main"/>' method='POST'>
			<table border=1>
				<tr>
					<td>Год:<input type='text' name='year' value='${form.year}'/><br/></td>
					<td>Список групп:
						<select name='groupId'>
							<c:forEach var='group' items='${form.groups}'>
								<c:choose>
									<c:when test='${group.id==form.groupId}'>
										<option value='${group.id}' selected><c:out value='${group.name}'/></option>
									</c:when>
									<c:otherwise>
										<option value='${group.id}'><c:out value='${group.name}'/></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td><input type='submit' name='getList' value='Обновить'/></td>
				</tr>
			</table>

			<p/><b>Список студентов для выбранных параметров:<b><br/>
			<table border=1>
				<tr>
					<th>&nbsp;</th>
					<th>Фамилия</th>
					<th>Имя</th>
					<th>Отчество</th>
				</tr>
				<c:forEach var='student' items='${form.students}'>
					<tr>
						<td><input type='radio' name='studentId' value='${student.id}'></td>
						<td><c:out value='${student.surname}'/></td>
						<td><c:out value='${student.name}'/></td>
						<td><c:out value='${student.patronymic}'/></td>
					</tr>
				</c:forEach>
			</table>
			<table border=1>
				<tr>
					<td><input type='submit' value='Add' name='Add'/></td>
					<td><input type='submit' value='Edit' name='Edit'/></td>
					<td><input type='submit' value='Delete' name='Delete'/></td>
				</tr>
			</table>

			<p/><b>Переместить студентов в группу<b><br/>
			<table border=1>
				<tr>
					<td>Год:<input type='text' name='newYear' value='${form.year}'/><br/></td>
					<td>Список групп:
						<select name='newGroupId'>
							<c:forEach var='group' items='${form.groups}'>
								<option value='${group.id}'><c:out value='${group.name}'/></option>
							</c:forEach>
						</select>
					</td>
					<td><input type='submit' name='MoveGroup' value='Переместить'/></td>
				</tr>
			</table>
		</form>
	</body>
</html>