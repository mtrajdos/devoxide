<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Typing Tester</title>
</head>
<body>

	<h1>Typing Tester Page</h1>
	<div>
		<h3>Fetched list of words:</h3>
	</div>
	<div>
		<a>${user.listofrandomwordsets}</a>
	</div>

	<div>
		<h3>Type in the same words:</h3>
	</div>
<%-- 	<t:form method="post" action="AddUserInput">
		<div>
			<label name="inputWordListValues">Here:</label>
		</div>
		<div>
			<textarea name="inputWordListValues" required="required" rows="3" cols="50"></textarea>
		</div>
		<p>
			<input type="submit" value="Add"/>
		</p>
	</t:form> --%>
</body>
</html>