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
<title>Insert title here</title>
</head>
<body>
	<a href="/Todo/createServlet">タスク追加</a>
	<div>
		<% if(type == null) { %>
			タスク一覧
			<a href="/Todo/IndexServlet?type=expired">期限切れタスク</a>
		<% }else { %>
			<a href="/Todo/IndexServlet">タスク一覧</a>
			期限切れタスク一覧
		<% } %>
		
	</div>
	<br>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>期限</th>
			<th></th>
		</tr>
		<c:forEach var="task" items="${taskList }">
		<tr>
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
</body>
</html>