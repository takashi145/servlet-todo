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
	<a href="/Todo/showServlet?id=${task.id }">戻る</a>
	<ul>
		<c:forEach var="error" items="${errorList }">
			<li>${error}</li>
		</c:forEach>
	</ul>
	<form action="/Todo/UpdateServlet" method="post">
		<input type="hidden" name="id" value="${task.id }">
		<div>
			<label for="task_name">タスク名</label>
			<input type="text" name="task_name" id="task_name" value="${task.task }">
		</div>
		<div>
			<label for="explanation">タスク詳細</label>
			<textarea name="explanation" id="explanation">${task.explanation }</textarea>
		</div>
		<div>
			<label for="deadline">期限</label>
			<input type="date" name="deadline" id="deadline" value="${task.deadline }">
		</div>
		<div>
			<input type="submit" value="更新">
		</div>
	</form>
</body>
</html>