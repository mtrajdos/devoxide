<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<body>
	<h2>Editing user ${user.username}</h2>

	<f:form method="post" action="EditUserSubmit" modelAttribute="user">
		<div>
			<f:label path="username">User name:</f:label>
			<f:input path="username" type="text" required="required" size="30" maxlength="80"/>
		</div>
		<div>
			<f:label path="password">Password:</f:label>
			<f:input path="password" type="password" required="required" size="30" maxlength="80"/>
		</div>
		<div>
			<f:label path="verified">Verify:</f:label>
			<f:checkbox path="verified"/>
		</div>
		<div>
			<div>Example of a radio button instead of a select</div> 
			<f:label path="userType">User Type:</f:label>
			<f:radiobuttons path="userType" items="${userTypes}" itemLabel="name"/>
		</div>
		<p>
			<input type="submit" value="Update"/>
		</p>
		<f:hidden path="id"/>
	</f:form>
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>