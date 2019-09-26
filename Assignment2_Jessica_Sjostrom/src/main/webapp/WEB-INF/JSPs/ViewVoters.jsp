<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Voters</title>
<style>
body {background-color: #D2C9D6}
h1   {color: #3e1b6b;}
button,input {background-color: #555555; color: white;}
div {align: center; font-family: "Trebuchet MS", Helvetica, sans-serif;}
table {
  border: 3px solid #000000;
  width: 50%;
  text-align: left;
  border-collapse: collapse;
  background: white;
}
td, th {
  border: 1px solid #000000;
  padding: 5px 4px;
}
thead {
  background: #CFCFCF;
  background: -moz-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: -webkit-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: linear-gradient(to bottom, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  border-bottom: 3px solid #000000;
}
thead th {
  font-weight: bold;
  color: #000000;
  text-align: left;
}
</style>
</head>
<body>

<div align="center">
<h1>View Voters</h1>
<br><br>

<table width="80%">
	<tr>
		<th>SIN</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Birthday</th>
		<th>Address</th>
		<th>Voted?</th>
	</tr>
<c:forEach items="${votersRegistered}" var="item">	
	<tr>
		<td>${item.sin}</td>
		<td>${item.fname}</td>
		<td>${item.lname }</td>
		<td>${item.birthday }</td>
		<td>${item.address }</td>
		<td>${item.voted } </td>
		<td><a href="<c:url value="/Edit/${item.sin}"/>">Edit</a></td>
		<td><a href="<c:url value="/Delete/${item.sin}"/>">Delete</a></td>					
</c:forEach>
</table>

<br><br>
<a href="/"><h2>Home</h2></a>
</div>
</body>
</html>