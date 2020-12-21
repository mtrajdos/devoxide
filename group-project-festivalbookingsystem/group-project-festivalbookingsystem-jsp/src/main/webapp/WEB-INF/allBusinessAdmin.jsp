<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Businesses</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
	<h2>All Businesses</h2>
	<div>
		<form action="SearchByLocation" method="post">
	<div>
		<label>Search by Location:</label> <input type="text" name="location"/>
	</div>
		<button type="submit">Search</button>
	</form>
	<br>
	</div>
	<div>
		<c:forEach items="${allBusinesses}" var="business">
			<div>
				<a href="DisplayBusiness?id=${business.business_id}">${business.businessName}: ${business.location}</a>
				<p>${business.businessType}</p>
				<p>Address: ${business.address}</p>
			</div>
			<p>-------------------------------------------------</p>
		</c:forEach>
	</div>

	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>