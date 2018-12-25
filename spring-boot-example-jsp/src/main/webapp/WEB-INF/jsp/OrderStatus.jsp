<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/static/css/app.css'/>"/>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css'/>"/>
<title>Status Orders</title>
</head>
<body>
	<div class="generic-container">
		<div class="well lead">Product Order Status</div>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="data">
					<tr>
						<td><c:out value="${data.productId}"/></td>
						<td><c:out value="${data.productName}"/></td>
						<td><c:out value="${data.orderStatus}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<span class="well floatRight">
			<a href="<c:url value='/newOrder'/>" class="btn btn-primary">Order Again.</a>
		</span>
	</div>
</body>
</html>