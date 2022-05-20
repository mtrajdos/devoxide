<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<style>h1{color: white}</style>
<style>h3{color: white}</style>
<style>label{color: white}</style>
<meta charset="ISO-8859-1">
<title>Register for Festival+</title>
</head>
<body>
	<h1>Registration Page</h1>
	<h3>Enter your user details</h3>
	<div>
		<form action="RegisterSubmit" method="post">
			${errorMessage}
			<div>
				<label>User name:</label>
				<input type="text" name="username"/>
			</div>
			<div>
				<label>Password</label>
				<input type="password" name="password"/>
			</div>
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>