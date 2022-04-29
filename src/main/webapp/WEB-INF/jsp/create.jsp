<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/Todo/IndexServlet">戻る</a>
	<form action="/Todo/createServlet" method="post">
		<div>
			<label for="task_name">タスク名</label>
			<input type="text" name="task_name" id="task_name">
		</div>
		<div>
			<label for="explanation">タスク詳細</label>
			<textarea name="explanation" id="explanation"></textarea>
		</div>
		<div>
			<label for="deadline">期限</label>
			<input type="date" name="deadline" id="deadline">
		</div>
		<div>
			<input type="submit" value="送信">
		</div>
	</form>
</body>
</html>