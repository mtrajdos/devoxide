<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<meta charset="ISO-8859-1">
<title>Adding a festival</title>
</head>

<body>
	<h2>Add a festival</h2>
	<f:form method="post" action="AddFestivalSubmit"
		modelAttribute="festival">

		<f:label path="festivalName">Festival name:</f:label>
		<div>
			<f:input path="festivalName" type="text" required="required"
				size="100" />
		</div>

		<f:label path="location">Location:</f:label>
		<div>
			<f:input path="location" type="text" required="required" size="50" />
		</div>

		<f:label path="startDate">Start date:</f:label>
		<div>
			<f:input path="startDate" type="datetime-local" required="required" />
		</div>

		<f:label path="endDate">End date:</f:label>
		<div>
			<f:input path="endDate" type="datetime-local" required="required" />
		</div>

		<f:label path="genre">Genre:</f:label>
		<div>
			<f:select path="genre" items="${genres}" itemLabel="name"
				required="required" />
		</div>
		<f:label path="baseTotalTickets">Total Capacity:</f:label>
		<div>
			<f:input path="baseTotalTickets" type="number" required="required" />
		</div>


		<f:label path="originalPrice">Ticket Price:</f:label>
		<div>
			<f:input path="originalPrice" type="number" required="required" />
		</div>
		<div>
			<f:label path="earlyBird">Early Bird Discount:</f:label>
			<f:checkbox path="earlyBird" />
			<br>
		</div>

		<div>
			<br>
			<button type="submit">Add Festival</button>

		</div>

	</f:form>
	<br>

	<div>
		<a href="Main">Home</a>
	</div>
</body>
</html>