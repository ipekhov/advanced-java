<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index jsp with bootstrap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
   	form {
   		border: 1px solid gray;
   		width: 350px;
   		margin-left: auto;
   		margin-right: auto;
   		margin-top: 100px;
   		padding: 30px;
   		display: grid;
   		grid-template-columns: 1fr 1fr;
   		grid-gap: 0.5em 0.5em;
   	}
   	#submit {
	   	grid-column: 2/3;
   	}
   	label {
   		justify-self: right;
   	}
    </style>
</head>
<body>
<p>
<c:out value="Hello JSTL"/>
</p>
<p>
<c:out value="${pageContext.servletContext.contextPath}"/>
</p>
<p>
<c:url value="/SampleServlet"/>
</p>
<form action="${pageContext.servletContext.contextPath}/users" method="POST">
	<label for="name">Name: </label>
	<input id="name" type="text" name="name" />
	
	<label for="password">Password: </label>
	<input id="password" type="password" name="password" />
	
	<input id="submit" type="submit" value="Submit" />
</form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>