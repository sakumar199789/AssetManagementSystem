<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asset Management System</title>
</head>
<body>
<center><h2>Session Expired</h2></center>
<%
	response.setHeader("Refresh","5;URL=HomePage.html");
	
%>
</body>
</html>