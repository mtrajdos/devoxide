<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="ISO-8859-1">
<title>Edit Festival</title>
</head>
<body>
	<h1>Edit Festival</h1>
	<f:form method="post" action="EditFestivalSubmit"
		modelAttribute="festival">
		<h3>${errorMessage}</h3>
		<div>
			<f:label path="festivalName">Festival name:</f:label>
			<f:input path="festivalName" type="text" required="required"
				size="30" maxlength="80" />
		</div>
		<div>
			<f:label path="location">Location:</f:label>
			<f:input path="location" type="text" required="required" size="30"
				maxlength="80" />
		</div>
		<div>
			<f:label path="startDate">Start Date:</f:label>
			<f:input path="startDate" type="datetime-local" required="required" size="30"
				maxlength="80" />
		</div>
		<div>
			<f:label path="endDate">End Date:</f:label>
			<f:input path="endDate" type="datetime-local" required="required" size="30"
				maxlength="80" />
		</div>
		<div>
			<f:label path="genre">Genre:</f:label>
			<f:select path="genre" items="${genres}" itemLabel="name"
				required="required" />
		</div>
		<div>
			<f:label path="festivalBands">Bands:</f:label>
			<f:select path="festivalBands" items="${bands}" itemLabel="bandName"
				multiple="multiple"></f:select>
		</div>
		<div>
			<f:label path="originalPrice">Ticket Price:</f:label>
			<f:input path="originalPrice" type="number" required="required" />
		</div>
		<div>
			<f:label path="onSiteAccommodation">Accommodation:</f:label>
			<f:input path="onSiteAccommodation" type="text" required="required"
				size="30" maxlength="180" />
		</div>
		<br>
		<div>
			<a href="AllStagesAdmin?id=${festival.festival_id}" >All Stages</a>
		</div>
		<div>
			<a href="CreateStage?id=${festival.festival_id}">Add Stage</a>
		</div>
		<br>
		<div>
			<f:label path="earlyBird">Early Bird Discount:</f:label>
			<f:checkbox path="earlyBird" />
		</div>

		<p>
			<input type="submit" value="Update" />
		</p>
		
		<f:hidden path="festival_id" />
		<f:hidden path="currentTickets" />
		<f:hidden path="baseTotalTickets" />

	</f:form>

	<div>
		<a href="Main">Home</a>
	</div>
</body>
</html>