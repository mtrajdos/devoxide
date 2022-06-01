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
<body>

	<h2>All Bands</h2>
	<div>
		<c:forEach items="${allBands}" var="band">
			<div>
				<a href="EditBand?id=${band.band_id}">${band.bandName}</a>
				<p>${band.genre}</p>
				<p>---------------------------------------</p>
			</div>
		</c:forEach>
		<br>
		
	</div>

	
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>