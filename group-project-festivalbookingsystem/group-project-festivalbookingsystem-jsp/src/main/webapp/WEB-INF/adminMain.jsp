<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="ISO-8859-1">
<title>Main</title>
</head>
<body>
		<h1>Logged In</h1>
			<h3>Hello ${user.firstname}.</h3>
	<div>
		<a href="Logout">Log out</a>
	</div>
	<div>
		<a href="AllFestivals">List All Festivals</a>
	</div>
	<div>
		<a href="AllBands">List All Bands</a>
	</div>
		<div>
		<a href="AllBusinessAdmin">List All Business</a>
	</div>
	
</body>
</html>
