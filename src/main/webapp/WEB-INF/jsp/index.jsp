<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String type = request.getParameter("type");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<div class="container col-md-6">
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
			<c:forEach var="task" items="${taskList }">
			<tr class="table table-secondary">
				<td>
					<c:out value="${task.task }" />
				</td>
				<td>
					<c:choose>
						<c:when test="<c:out value='${task.deadline }' /> == null">
							無期限
						</c:when>
						<c:otherwise>
							<c:out value="${task.deadline }" />
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="/Todo/showServlet?id=<c:out value='${task.id }'/>">詳細</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="text-end">
			<button onclick="location.href='/Todo/createServlet'" class="btn btn-primary">タスク追加</button>
		</div>
	</div>
</body>
</html>