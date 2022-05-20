<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<meta charset="ISO-8859-1">
<title>Edit band</title>
</head>
<body>
<h1>Edit Band</h1>
<f:form method="post" action="EditBandSubmit" modelAttribute="band">
	<h3>${errorMessage}</h3>
	<div>
		<f:label path="bandName">Band name:</f:label>
		<f:input path="bandName" type="text" required="required" size="30" maxlength="80"/>
	</div>
	<div>
		<f:label path="genre">Genre:</f:label>
		<f:select path="genre" items="${genres}" itemLabel="name" required="required" />
	</div>
	
	<p>
		<input type="submit" value="Update"/>
	</p>
	<f:hidden path="band_id"/>
</f:form>

<div>
			<a href="Main">Home</a>
		</div>

</body>
</html>