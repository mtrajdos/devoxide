<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${business.businessName}</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
	<h1>${business.businessName} : ${business.location}</h1>
	<div>
		<p>Business Type: ${business.businessType.getName()}</p>
	</div>
	<div>
		<p>Address: ${business.address}</p>
	</div>
	<div>
		<p>Phone Number: ${business.phoneNumber}</p>
	</div>
	<div>
		<p>Email: ${business.email}</p>
	</div>
	
	<div>
			<a href="Main">Home</a>
		</div>
</body>
</html>