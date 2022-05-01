<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<div class="container col-md-6 m-5">
		<div class="mb-3">
			<a href="/Todo/IndexServlet">戻る</a>
		</div>
		<div>
			<div class="mb-3">
				タスク名
				<p class="border border-2 rounded p-2 mt-2">${task.task }</p>
			</div>
			<div class="mb-3">
				タスク詳細
				<p class="border border-2 rounded p-2 mt-2">
					<c:choose>
						<c:when test="${empty task.explanation }">
							---
						</c:when>
						<c:otherwise>
							${task.explanation }
						</c:otherwise>
					</c:choose>
				</p>
			</div>
			<div class="mb-5">
				期限
				<p class="border border-2 rounded p-2 mt-2">
					<c:choose>
						<c:when test="${empty task.deadline }">
							無期限
						</c:when>
						<c:otherwise>
							${task.deadline }
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>
		<div class="d-flex justify-content-around">
			<button onclick="location.href='/Todo/UpdateServlet?id=${task.id}'" class="btn btn-primary">編集</button>
			<form action="/Todo/DeleteServlet" method="post">
				<input type="hidden" name="id" value="${task.id }">
				<button type="submit" class="btn btn-danger">削除</button>
			</form>
		</div>
	</div>
	
</body>
</html>