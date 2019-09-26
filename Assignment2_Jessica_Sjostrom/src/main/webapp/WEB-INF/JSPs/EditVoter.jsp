<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Voter</title>
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
<h1>Edit Voter</h1>
<br><br>

<c:url var="url" value="/EditForm"/>
<form:form action="${url}" modelAttribute="voter" method="POST">

<table>
<tr>
	<td>SIN: </td>
	<td><form:input type="hidden" path="sin" name="sin" value="${editSin }"/>${editSin} </td>
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
	<input type="submit" value="Save Changes" /> </td>
</tr>
</table>
</form:form>

</body>
</html>