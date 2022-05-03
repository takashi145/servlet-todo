<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Task,java.util.*" %>
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
	<div class="container col-md-6">
		<jsp:include page="/WEB-INF/jsp/components/flash.jsp" />
		<div class="mt-4 d-flex justify-content-around">
			<div>
				<% if(type == null) { %>
					<span class="bg-secondary text-white rounded-pill p-3">タスク一覧</span>
				<% }else { %>
					<a href="/Todo/IndexServlet">タスク一覧</a>
				<% } %>
			</div>
			<div>
				<% if(type != null && type.equals("expired")) { %>
					<span class="bg-secondary text-white rounded-pill p-3">期限切れ</span>
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
				<th></th>
			</tr>
			<% for(Task task : taskList) {%>
				<tr>
					<td><%=task.getTask() %></td>
					<td>
						<% if(task.getDeadline() == null) {%>
							期限なし
						<% }else { %>
							<%=task.getDeadline() %>
						<% }%>
					</td>
					<td>
						<a href="/Todo/ShowServlet?id=<%=task.getId() %>">詳細</a>
					</td>
				</tr>
			<% } %>
		</table>
		<div class="text-end">
			<button onclick="location.href='/Todo/CreateServlet'" class="btn btn-primary">タスク追加</button>
		</div>
	</div>
</body>
</html>