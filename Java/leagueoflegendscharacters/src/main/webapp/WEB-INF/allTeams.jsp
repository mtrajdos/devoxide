<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Teams</title>
</head>
<body>
	<h2>All Teams</h2>
	<div>${errorMessage}</div>
	<button onclick="goBack()">Go Back</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>
	<div>
		<c:forEach items="${teams}" var="team">
			<div>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
			<div>
				<font size="25">${team.name}</font>
			</div>
			<br>
			<c:forEach items="${team.characters}" var="character">
				<div>
					<h3>Character:</h3>
				</div>
				<div>${character.name}</div>
				<div>
					<h3>Position:</h3>
				</div>
				<div>${character.position.getName()}</div>
			</c:forEach>
			<div></div>

			<div>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
		</c:forEach>
	</div>
</body>
</html>
