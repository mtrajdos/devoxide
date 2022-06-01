<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Festivals</title>
</head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<style>
body {background-image: none !important;}
h1 {color: black}
h2 {color: black}
h3 {color: black}
p {	color: red}
</style>
<style>body {background-image:none !important;}</style><body>
	<font size="6"> <h1>All Festivals</h1></font>
	<div>
		<c:forEach items="${allFestivals}" var="festival">
			<div>
				<a href="EditFestival?id=${festival.festival_id}">${festival.festivalName}</a>
					<h2>${festival.startDate.toLocalDate()} - ${festival.endDate.toLocalDate()}</h2>
			
			</div>
			<div>
				<h3>Bands in ${festival.festivalName}:</h3>
				<c:forEach items="${festival.festivalBands}" var="band">
					<a>${band.bandName}</a>
					
					<br>
				</c:forEach>
				
				<c:choose>
					<c:when test="${festival.originalPrice==festival.ticketPrice}">
						<p>Current price: &pound ${festival.ticketPrice}</p>
						<br />
					</c:when>
					<c:otherwise>
						<p>Current price: &pound ${festival.ticketPrice}</p>
						<del> Original price: &pound ${festival.originalPrice} </del>
						<br>
					</c:otherwise>
				</c:choose>
				<br>
				<p> ---------------------------------------------------------</p>
		</c:forEach>
	</div>

	<form action="AddFestival">
		<p>
			<input type="submit" value="Add a festival" />
		</p>
	</form>
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>