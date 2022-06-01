<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Tickets</title>
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
	<font size="6"> <h1>All Tickets</h1></font>
	
		<c:forEach items="${user.userTickets}" var="ticket">
			
			 <p>${ticket.ticketName}</p>
	      	<p>Start date: ${ticket.ticketStartDate.toLocalDate()} ${ticket.ticketStartDate.toLocalTime()}</p>
	    	 <p>End date: ${ticket.ticketEndDate.toLocalDate()} ${ticket.ticketEndDate.toLocalTime()}</p>
	    	 <br>
	    </c:forEach>
				
			
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>