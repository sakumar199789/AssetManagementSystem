<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Asset Management System</title>
<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
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
      <li class=""><a href="Admin.jsp">Home</a></li>
     <!--  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li> -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
   <!--    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
      <li><a href="AssetController?action=logout" ><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
	</ul>
    
  </div>
</nav>
<table id="customers" align=center>
  <tr>
    <th>Image</th>
    <th>Asset Id</th>
    <th>Asset Name</th>
    <th>Asset Description</th>
     <th>Quantity</th>
    <th>Status</th>
  </tr>
  <c:forEach items="${sessionScope.assetlist}" var="id">

   
	<tr><td><img src="Images/${id.image}" width=100px height=100px></td><td><c:out value="${id.assetId}"/></td><td><c:out value="${id.assetName}"/></td><td><c:out value="${id.assetDes}"/></td><td><c:out value="${id.quantity}"/></td><td><c:out value="${id.status}"/></td><td><a href="AssetController?action=edit&id=${id.assetId}&name=${id.assetName}&des=${id.assetDes}&quantity=${id.quantity}&status=${id.status}">Edit</a></td></tr>
	   
	 </c:forEach>
 
</table>
</body>
</html>