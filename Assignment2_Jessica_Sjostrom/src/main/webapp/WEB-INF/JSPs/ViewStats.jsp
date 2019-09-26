<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Stats</title>
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
<h1>Voting Stats</h1>
<br>

<table>
	<tr><th colspan="2">Vote Totals (%)</th></tr>
	<tr>
		<th>Party</th>
		<th>Percent</th>
	</tr>
	<tr>
		<td>Liberals</td>
		<td>${libs }</td>
	</tr>
	<tr>
		<td>Conservatives</td>
		<td>${cons }</td>
	</tr>
	<tr>
		<td>New Democratic Party</td>
		<td>${ndps }</td>
	</tr>
	<tr>
		<td>Green Party</td>
		<td>${greens }</td>
	</tr>
	<tr>
		<td>Bloc Quebecois</td>
		<td>${blocs }</td>
	</tr>
</table>

<br><br>

<h2>Percentage of eligible voters that voted: ${percentVoted }</h2>

<br><br>

<table>
	<tr><th colspan="2">Voter statistics</th></tr>
	<tr>
		<th>Age Range</th>
		<th>Percent</th>
	</tr>
	<tr>
		<td>18-30 </td>
		<td>${yrs18 }</td>
	</tr>
	<tr>
		<td>30-45 </td>
		<td>${yrs30 }</td>
	</tr>
	<tr>
		<td>45-60 </td>
		<td>${yrs45 }</td>
	</tr>
	<tr>
		<td>60+ </td>
		<td>${yrs60 }</td>
	</tr>
</table>

<br><br>
<a href="/"><h2>Home</h2></a>
</div>
</body>
</html>