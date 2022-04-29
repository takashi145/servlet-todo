<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/Todo/createServlet">タスク追加</a>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>期限</th>
			<th></th>
		</tr>
		<c:forEach var="task" items="${taskList}">
		<tr>
			<td><c:out value="${task.task}"/></td>
			<td><c:out value="${task.deadline}"/></td>
			<td>
				<a href="/Todo/showServlet?id=${task.id}">詳細</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>