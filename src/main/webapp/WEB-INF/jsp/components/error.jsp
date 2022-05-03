<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="list-unstyled text-danger text-center">
	<c:forEach var="error" items="${errorList }">
		<li>${error}</li>
	</c:forEach>
</ul>