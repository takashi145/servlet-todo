<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String message = (String)session.getAttribute("message");
String alert = (String)session.getAttribute("alert");
%>
<div class="text-center mt-3">
	<% if(message != null){ %>
		<p class="bg-success p-2 rounded text-white"><%=message %></p>
	<% }else if(alert != null){ %>
		<p class="bg-danger p-2 rounded text-white"><%=alert %></p>
	<% } %>
</div>