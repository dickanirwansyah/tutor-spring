<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- css 
<link href="/static/css/bootstrap.css" rel="stylesheet"/>
<link href="/static/css/app.css" rel="stylesheet"/>
css -->

<link href='<c:url value="/static/css/bootstrap.css"/>' rel="stylesheet"/>
<link href='<c:url value="/static/css/app.css"/>' rel="stylesheet"/>

<title>Example ActiveMQ</title>
</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<h3>Example ActiveMQ</h3>
			<div class="generic-container">
				<h4>Example Java Messaging Service Cloud Bus With ActiveMQ</h4>
				<span class="well floatRight">
					<a class="btn btn-primary" href='<c:url value="/newOrder"/>'>Place an order</a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>