<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
</head>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<body>
	<h2>All Users</h2>
	<div>
		<c:forEach items="${allUsers}" var="user">
			<div>
			
				<p>${user.username}</p>
			</div>
		</c:forEach>
	</div>
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>