<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stages</title>
</head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<body>
<h1>Stages</h1>
<div>
	<c:forEach items="${allStages}" var="stage">
		<div>
			<p><b> Stage Name: ${stage.stageName} </b></p>
		</div>
		<div>
			<c:forEach items="${stage.bandsInStage}" var="band" >
				<p> Band: ${band.bandName} </p>
			</c:forEach>
			<p>------------------------------------------</p>
		</div>
	</c:forEach>
</div>
</body>
</html>