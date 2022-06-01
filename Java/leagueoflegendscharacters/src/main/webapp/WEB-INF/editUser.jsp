<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
</head>
<body>
	<h2>
		Editing user ${user.username}
		<button onclick="goBack()">Go Back</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>
	</h2>
	<f:form method="post" action="EditUserSubmit" modelAttribute="user">
		<div>
			<f:label path="username">User name:</f:label>
			<f:input path="username" type="text" required="required" size="30"
				maxlength="80" />
		</div>
		<div>
			<f:label path="password">Password:</f:label>
			<f:input path="password" type="password" required="required"
				size="30" maxlength="80" />
		</div>
		<div>
			<f:label path="verified">Verify:</f:label>
			<f:checkbox path="verified" />
		</div>
		<f:label path="userType">User Type:</f:label>
		<f:radiobuttons path="userType" items="${userTypes}" itemLabel="name" />
		<p>
			<input type="submit" value="Update" />
		</p>
		<f:hidden path="id" />
	</f:form>
</body>
</html>