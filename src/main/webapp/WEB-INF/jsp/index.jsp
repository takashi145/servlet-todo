<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Task,java.util.*,java.sql.*" %>
<%
String type = request.getParameter("type");
List<Task> taskList = (List<Task>)request.getAttribute("taskList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoリスト</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/components/header.jsp" />
	<div class="container col-md-8">
		<jsp:include page="/WEB-INF/jsp/components/flash.jsp" />
		<div class="mt-4 d-flex justify-content-around">
			<div>
				<% if(type == null) { %>
					<span class="text-black fs-6">タスク一覧</span>
				<% }else { %>
					<a href="/Todo/IndexServlet">タスク一覧</a>
				<% } %>
			</div>
			<div>
				<% if(type != null && type.equals("expired")) { %>
					<span class="text-black fs-6">期限切れ</span>
				<% }else { %>
					<a href="/Todo/IndexServlet?type=expired">期限切れ</a>
				<% } %>
			</div>
		</div>
		<br>
		<table class="table text-center">
			<tr class="table table-dark text-white">
				<th>タスク名</th>
				<th>期限</th>
				<th>状態</th>
				<th></th>
			</tr>
			<% for(Task task : taskList) {%>
				<tr>
					<td><%=task.getTitle() %></td>
					<td>
						<% if(task.getDeadline() == null) {%>
							期限なし
						<% }else { %>
							<%=task.getDeadline() %>
						<% } %>
					</td>
					<td>
						<% if(task.getStatus()) { %>
							<span class="text-success">完了</span>
						<% }else { %>
							<span class="text-danger">未完了</span>
						<% } %>
					</td>
					<td>
						<a href="/Todo/ShowServlet?id=<%=task.getId() %>">詳細</a>
					</td>
				</tr>
			<% } %>
		</table>
		<% if(type == null) { %>
		<div class="text-end">
			<button onclick="location.href='/Todo/CreateServlet'" class="btn btn-primary">タスク追加</button>
		</div>
		<% } %>
	</div>
</body>
</html>