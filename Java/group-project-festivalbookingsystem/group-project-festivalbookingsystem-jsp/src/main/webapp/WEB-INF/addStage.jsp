<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="UTF-8">
<title>Add Stage</title>
</head>
<body>

<f:form method="post" action="AddStageSubmit" modelAttribute="stage">
	<div>
		<f:label path="stageName">Stage Name:</f:label>
		<f:input path="stageName" type="text" required="required"/>
	</div>
	<div>
		<f:label path="bandsInStage">Band:</f:label>
		<f:select path="bandsInStage" items="${allBands}" itemLabel="bandName"/>
	</div>
	<p>
		<input type="submit" value="Add"/>
	</p>
	<f:hidden path="stage_id"/>	
</f:form>

</body>
</html>