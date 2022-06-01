<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="ISO-8859-1">
<title>Main</title>
</head>
<body>
<p>${errorMessage}</p>
	<font size="5"><h1>Hello ${user.getFirstname()}, your basket contains:</h1></font>
	<br>

<c:forEach items="${user.getUserBasketItems()}" var="item">
		<div>
			<p> <h2><b>Product Name: ${item.getItemName()} </b></h2></p>
		</div>
		<div>
			<p> Description: ${item.getItemDescription()} </p>
		</div>
		<div>
			<p> Price: &pound${item.getItemPrice()} </p>
		</div>
		<div>
			<p>-------------------------------------------------------</p>
		</div>
</c:forEach>

<div><h2>Total price : &pound${user.getTotalBasketPrice()}</h2></div>
	<br>
	<div>
	<form action="BasketCheckoutSubmit" method="post">
		<p>
			<input type="submit" value="Purchase Items" />
		</p>
	</form>
	</div>
</body>
</html>
