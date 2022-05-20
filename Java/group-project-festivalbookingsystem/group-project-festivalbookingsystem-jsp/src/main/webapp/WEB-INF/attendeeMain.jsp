<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<a href="AllFestivalsAttendee">List All Festivals</a>
	</div>
	<br>

	<div>
		<p>Early Bird Tickets Still Available At These Festivals
	</div>
	<div>
		<c:forEach items="${earlyBirdList}" var="festival">
			<p>${festival.festivalName}</p>
			
		</c:forEach>
	</div>
	
	<div>
		<h2>${user.firstname} ${user.lastname} Funds: &pound${user.userFunds}</h2>
	</div>
	
	<div>
	<h2>Your Festival Tickets</h2>
	<c:forEach items="${user.userTickets}" var="ticket">
	<b><p>${ticket.ticketName}</p></b>
	<p>Start Date: ${ticket.ticketStartDate.toLocalDate()} </p>
	<p>Arrive By: ${ticket.ticketStartDate.toLocalTime()}</p>

	<p>End Date: ${ticket.ticketEndDate.toLocalDate()}</p>
	<p>Leave By: ${ticket.ticketEndDate.toLocalTime()}</p>
	<br>
	</c:forEach>
	</div>
	<input type="hidden" value="${user_id}"/>
</body>
</html>
