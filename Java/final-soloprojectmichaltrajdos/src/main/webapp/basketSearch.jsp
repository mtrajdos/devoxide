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
<title>Search basket by ID</title>
</head>
<body>
	<h1>Basket search page</h1>
	<br>
	<h3>Enter your user ID:</h3>
	<br>
	<div>
		<form action="BasketCheckout" method="post">
			<p>${errorMessage}</p>
			<div>
				<label>User id:</label>
				<input type="number" name="user_id" required="required"/>
			</div>
			<br>
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>