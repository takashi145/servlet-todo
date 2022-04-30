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
	<a href="/Todo/IndexServlet">戻る</a>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td>${task.task }</td>
		</tr>
		<tr>
			<th>タスク詳細</th>
			<td>${task.explanation }</td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<c:choose>
					<c:when test="${task.deadline} == null">
						無期限
					</c:when>
					<c:otherwise>
						${task.deadline }
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	<div>
		<button onclick="location.href='/Todo/UpdateServlet?id=${task.id}'">編集</button>
		<form action="/Todo/DeleteServlet" method="post">
			<input type="hidden" name="id" value="${task.id }">
			<input type="submit" value="削除">
		</form>
	</div>
</body>
</html>