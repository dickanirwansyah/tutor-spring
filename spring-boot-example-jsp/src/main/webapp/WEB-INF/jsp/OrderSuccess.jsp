<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/static/css/app.css'/>"/>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css'/>"/>
<title>Successfully</title>
</head>
<body>
	<div class="generic-container">
		<div class="alert alert-success lead">
			${success}
		</div>
		<span class="well floatRight">
			<a href="<c:url value='/newOrder'/>">Order Again !</a>
		</span>
		<span class="well floatRight">
			<a href="<c:url value='/checkStatus'/>">Check Status</a>
		</span>
	</div>
</body>
</html>