<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asset Management System</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  	.navbar-default{
  		background-color:#2f2f1e;
  	
  	}
  	
  
  </style>
</head>
<body>
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">AssetManagement System</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="Manager.jsp">Home</a></li>
     <!--  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li> -->
      <li><a href="AssetController?action=RaiseRequest">Raise a Request</a></li> 
        <li><a href="AssetController?action=viewrequeststatus">View Request Status</a></li> 
        </ul>
        
    <ul class="nav navbar-nav navbar-right">
   <!--    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
      <li><a href="AssetController?action=logout" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	</ul>
    
    
  </div>
</nav>
<form action="AssetController">
Enter Request Id:<input type=number name="requestId">
<input type="submit" name="action" value="ViewStatus">
</form>

</body>
</html>