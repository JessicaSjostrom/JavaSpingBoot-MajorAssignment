<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style>
body {background-color: #D2C9D6}
h1   {color: #3e1b6b;}
button,input {background-color: #555555; color: white;}
div {align: center; font-family: "Trebuchet MS", Helvetica, sans-serif;}
</style>
</head>
<body>

<div align="center">
<h1>Register to Vote</h1>
<br>

<c:url var="url" value="/addRegister" />
<form:form action="${url }" modelAttribute="voter" method="post">
<table>
<tr>
	<td>SIN: </td>
	<td><form:input type="text" path="sin" name="sin" /></td>
</tr>
<tr>
	<td>First name: </td>
	<td><form:input type="text" path="fname" name="fname"/></td>
</tr>
<tr>
	<td>Last name: </td>
	<td><form:input type="text" path="lname" name="lname" /></td>
</tr>
<tr>
	<td>Birthday: </td>
	<td><form:input type="text" path="birthyear" placeholder="YYYY" maxlength="4" style="width:50px"/>
	<form:input type="text" path="birthmon" placeholder="MM" maxlength="2" style="width:25px"/>
	<form:input type="text" path="birthdate" placeholder="DD"  maxlength="2" style="width:25px"/></td>
</tr>
<tr>
<td>Address: </td>
<td><form:input type="text" path="address" /></td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="Register" /> </td>
</tr>
</table>
</form:form>

<br>

<h2>${registerResult }</h2>

<br>
<a href="/"><h2>Home</h2></a>

</div>
</body>
</html>