<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vote</title>
<style>
body {background-color: #D2C9D6}
h1   {color: #3e1b6b;}
button,input {background-color: #555555; color: white;}
div {align: center; font-family: "Trebuchet MS", Helvetica, sans-serif;}
</style>
</head>
<body>

<div align="center">
<h1>Cast Your Vote</h1>
<br>

<c:url var="url" value="/addVoteForm/${sin}" />
<form:form action="${url}" modelAttribute="vote" method="post">
SIN: <form:input type="text" path="sin" name="sin" value="${param.sin}"/><br />
Vote: <form:select path="voteChoice" items="${vote.parties }" />
	<br>
<input type="submit" value="Cast Your Vote" />
</form:form>

<br>

<h2>Status: ${voteResult}</h2>

<br>
<a href="/"><h2>Home</h2></a>
</div>
</body>
</html>