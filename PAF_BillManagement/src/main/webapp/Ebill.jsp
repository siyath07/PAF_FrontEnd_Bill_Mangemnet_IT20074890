<%@page import="model.Ebill" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBill Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<h1>Bill Management</h1>
	
		<form id="formEbill" name="formEbill" method="post" action="Ebill.jsp">
			Bill ID:<input id="billID" name="billID" type="text" class="form-control"><br>
			Customer ID:<input id="cusID" name="cusID" type="text" class="form-control"><br>
			Amount of Units:<input id="unit" name="unit" type="text" class="form-control"><br>
			Total Amount:<input id="amount" name="amount" type="text" class="form-control"><br>
			
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
		</form>
	
	<br>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<div id="divItemsGrid">
	<%
 		Ebill noteObj = new Ebill();
 		out.print(noteObj.readEbill());
	%>
	</div>
	
	</div>
</body>
</html>