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

<body>
	<font size="6"> <h1>All Festivals</h1></font>
	<div>
	<br>
		<c:forEach items="${allFestivals}" var="festival">
			<div>
				<h2>${festival.festivalName}</h2>
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
						<br />
	
					</c:otherwise>
				</c:choose>
				
				<div>
				<br>
				<a href="BuyTicket?id=${festival.festival_id}">Buy Ticket</a>
				</div>
				<div>
				<a href="ViewAllStages?id=${festival.festival_id}" > View Stages</a>
				</div>
				<p> ---------------------------------------------------------</p>
				</div>
				
		</c:forEach>
	</div>
	<div>
		<a href="Main">Home</a>
	</div>
</body>
</html>