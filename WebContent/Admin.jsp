<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="false"
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
      <li class="active"><a href="#">Home</a></li>
     <!--  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li> -->
      <li><a href="AssetController?action=addnewasset">Add New Asset</a></li> 
        <li><a href="AssetController?action=modify">Modify Asset</a></li> 
          <li><a href="AssetController?action=viewrequest">View Request for the Asset</a></li> 
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Reports<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="AssetController?action=GenerateReport&operation=AllocatedAssets">Allocated Assets</a></li>
          <li><a href="AssetController?action=GenerateReport&operation=UnallocatedAssets">Unallocated Assets</a></li>
          
        </ul>
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Export<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="AssetController?action=export&operation=AllocatedAssets">Allocated Assets</a></li>
          <li><a href="AssetController?action=export&operation=UnallocatedAssets">Unallocated Assets</a></li>
          
        </ul>
      </li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
  
      <li><a href="AssetController?action=logout" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	</ul>
   </div>
</nav>
	
	<center><h2>Welcome Admin</h2></center>


</body>
</html>