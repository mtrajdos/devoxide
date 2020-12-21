<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Characters</title>
</head>
<body>
	<h2>All Characters</h2>
	<div><p>${errorMessage}</p></div>
	<a href="AddCharacter">Add a new character</a>
	<br><br>
	<div>
		<button onclick="goBack()">Go Back</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>
	</div>

	<div>
		<c:forEach items="${characters}" var="character">
			<div>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
			<div>
				<font size="25"><a href="EditCharacter?id=${character.id}">${character.name}</a></font>
			</div>
			<br>
			<div>
				<h3>Position: ${character.position.getName()}</h3>
			</div>
			<div></div>
			<div>
				<h3>Damage Source: ${character.damageSource.getName()}</h3>
			</div>
			<br>
			<div>
				<h3>Q ability: ${character.titleQ}</h3>
			</div>
			<div></div>
			<div>${character.abilityQDescription}</div>
			<div>
				<h3>W ability: ${character.titleW}</h3>
			</div>
			<div></div>
			<div>${character.abilityWDescription}</div>
			<div>
				<h3>E ability: ${character.titleE}</h3>
			</div>
			<div></div>
			<div>${character.abilityEDescription}</div>
			<div>
				<h3>R ability: ${character.titleR}</h3>
			</div>
			<div></div>
			<div>${character.abilityRDescription}</div>
			<div>
				<h3>Passive ability: ${character.titlePassive}</h3>
			</div>
			<div></div>
			<div>${character.passiveAbilityDescription}</div>
			<div>------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
		</c:forEach>
	</div>
</body>
</html>
