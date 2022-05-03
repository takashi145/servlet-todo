<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoリスト</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/components/header.jsp" />
	<div class="container col-md-6 m-5 mx-auto">
		<div class="m-3">
			<a href="/Todo/ShowServlet?id=${task.id }">戻る</a>
		</div>
		<jsp:include page="/WEB-INF/jsp/components/error.jsp" />
		<form action="/Todo/UpdateServlet" method="post">
			<input type="hidden" name="id" value="${task.id }">
			<div class="mb-3">
				<label for="task_name" class="form-label">タスク名</label>
				<input type="text" name="task_name" id="task_name" class="form-control" value="${task.task }" placeholder="タスク名を30文字以内で入力してください。">
			</div>
			<div class="mb-3">
				<label for="explanation" class="form-label">タスク詳細</label>
				<textarea name="explanation" id="explanation" class="form-control" rows="3" wrap="hard" placeholder="タスクの詳細を200文字以内で入力してください。">${task.explanation }</textarea>
			</div>
			<div class="mb-5">
				<label for="deadline" class="form-label">期限</label>
				<input type="date" name="deadline" id="deadline" class="form-control" value="${task.deadline }">
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary w-50">更新</button>
			</div>
		</form>
	</div>
</body>
</html>