<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>期限</th>
			<th></th>
		</tr>
		<% for(int i=0; i<10; i++) { %>
		<tr>
			<td>タスク<%=i %></td>
			<td>期限</td>
			<td>
				<button>詳細</button>
			</td>
		</tr>
		<% } %>
	</table>
</body>
</html>