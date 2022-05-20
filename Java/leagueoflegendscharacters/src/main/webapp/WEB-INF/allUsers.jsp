<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
</head>
<body>
	<h2>
		All Users
		<button onclick="goBack()">Go Back</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>
	</h2>
	<div>
		<c:forEach items="${allUsers}" var="user">
			<div>
				<a href="EditUser?id=${user.id}">${user.username}</a>
			</div>
		</c:forEach>
	</div>

	<form action="AddUser">
		<p>
			<input type="submit" value="Add a user" />
		</p>
	</form>
	
</body>
</html>