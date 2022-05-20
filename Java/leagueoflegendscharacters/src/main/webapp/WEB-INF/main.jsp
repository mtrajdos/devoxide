<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>
</head>
<body>
	<div>
		<a href="Logout">Log out</a>
	</div>
	<div>
		<a href="AllUsers">Manage Users</a>
	</div>
	<div>
		<a href="AllCharacters">All Characters</a>
	</div>
	
	<div>
		<a href="AllTeams">All Teams</a>
	</div>
	
	<h1>Logged In</h1>
	<h3>Hello ${user.username}.</h3>
</body>
</html>
