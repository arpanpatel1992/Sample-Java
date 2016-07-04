<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Contact</title>
</head>
<body>
	<h1>${contact.name} </h1>
		<ul>
			<li>street : ${address.street}</li>
			<li>city : ${address.city} </li>
			<li>state : ${address.state} </li>
			<li>zip : ${address.zip} </li>
		</ul>
	<a href="contacts">Back to contact list </a>
	<a href="contact?edit&id=${contact.id}">Edit contact</a>
</body>
</html>