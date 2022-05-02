<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク追加</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<div class="container col-md-6 m-5 mx-auto">
		<div class="m-3">
			<a href="/Todo/IndexServlet">一覧に戻る</a>
		</div>
		<ul class="list-unstyled text-danger text-center">
			<c:forEach var="error" items="${errorList }">
				<li>${error}</li>
			</c:forEach>
		</ul>
		<form action="/Todo/createServlet" method="post">
			<div class="mb-3">
				<label for="task_name" class="form-label">タスク名</label>
				<input type="text" name="task_name" id="task_name" class="form-control" placeholder="タスク名を30文字以内で入力してください。">
			</div>
			<div class="mb-3">
				<label for="explanation" class="form-label">タスク詳細</label>
				<textarea name="explanation" id="explanation" class="form-control" placeholder="タスクの詳細を200文字以内で入力してください。"></textarea>
			</div>
			<div class="mb-5">
				<label for="deadline" class="form-label">期限</label>
				<input type="date" name="deadline" id="deadline" class="form-control">
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary w-50">追加</button>
			</div>
		</form>
	</div>
</body>
</html>