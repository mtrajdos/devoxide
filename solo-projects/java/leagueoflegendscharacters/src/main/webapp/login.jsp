<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login To League of Legends Characters</title>
</head>
<body>
	<h1>Log in Page</h1>
	<h3>Enter your user details</h3>
	<div>
		<form action="LoginSubmit" method="post">
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