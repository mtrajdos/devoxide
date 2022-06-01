<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit character</title>
</head>
<body>
	<h2>Editing character: ${character.name}</h2>
	<f:form method="post" action="EditCharacterSubmit" modelAttribute="character">
				<div>
			<f:label path="name">Name:</f:label>
			<div><f:input path="name" type="text" required="required" size="10"/></div>
		</div>
 		<div>
			<f:label path="position">Position:</f:label>
			<div><f:select path="position" items="${positions}" itemLabel="name" required="required" /></div>
		</div>
		<div>
			<f:label path="damageSource">Damage Source:</f:label>
			<div><f:select path="damageSource" items="${damagesources}" itemLabel="name" required="required" /></div>
		</div>
		<div>
		<br>
			<f:label path="titleQ">Ability Q name:</f:label>
			<div><f:input path="titleQ" type="text" required="required" /></div>
		</div>
		<div>
			<f:label path="abilityQDescription">Ability Q Description:</f:label>
			<div><f:input path="abilityQDescription" type="text" required="required" size="100"/></div>
		</div>
		<br>
		<div>
			<f:label path="titleW">Ability W name:</f:label>
			<div><f:input path="titleW" type="text" required="required" /></div>
		</div>
		<div>
			<f:label path="abilityWDescription">Ability W Description:</f:label>
			<div><f:input path="abilityWDescription" type="text" required="required" size="100"/></div>
		</div>
		<br>
		<div>
			<f:label path="titleE">Ability E name:</f:label>
			<div><f:input path="titleE" type="text" required="required" /></div>
		</div>
		<div>
			<f:label path="abilityEDescription">Ability E Description:</f:label>
			<div><f:input path="abilityEDescription" type="text" required="required" size="100"/></div>
		</div>
		<br>
		<div>
			<f:label path="titleR">Ability R name:</f:label>
			<div><f:input path="titleR" type="text" required="required" /></div>
		</div>
		<div>
			<f:label path="abilityRDescription">Ability R Description:</f:label>
			<div><f:input path="abilityRDescription" type="text" required="required" size="100"/></div>
		</div>
		<br>
		<div>
			<f:label path="titlePassive">Passive Ability name:</f:label>
			<div><f:input path="titlePassive" type="text" required="required" /></div>
		</div>
		<div>
			<f:label path="passiveAbilityDescription">Passive Ability description:</f:label>
			<div><f:input path="passiveAbilityDescription" type="text" required="required" size="100"/></div>
		</div>
		<br>
		<div>
			<button type="submit">Edit Character</button>
		</div>
		<f:hidden path="id"/>
	</f:form>
	
	<f:form method="post" action="EditCharacterSubmitDelete" modelAttribute="character">
	<div>
		<button type="submit">Delete Character</button>
	</div>
	<f:hidden path="id"/>
	</f:form>
	
</body>
</html>