<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="ISO-8859-1">
<title>Editing Stages</title>
</head>
<body>
<h1>Edit Stages</h1>
<f:form method="post" action="EditStageSubmit" modelAttribute="stage">
	<div>
		<f:label path="stageName">Stage Name:</f:label>
		<f:input path="stageName" type="text" required="required"/>
	</div>
	<div>
		<f:label path="bandsInStage">Band:</f:label>
		<f:select path="bandsInStage" items="${bands}" itemLabel="bandName" required="required"/>
	</div>
	<p>
		<input type="submit" value="Update"/>
	</p>
	<f:hidden path="stage_id"/>	
</f:form>
<div>
	<a href="Main">Home</a>
</div>
</body>
</html>