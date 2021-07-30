<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Asset Management System</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body, html {
    height: 100%;
    font-family: Arial, Helvetica, sans-serif;
}
* {
    box-sizing: border-box;
}
#AddAsset{
    border-collapse: separate;
    border-spacing: 15px;

}


.bg-img {
    /* The image used */
    background-image: url("image1.jpg");
    min-height: 750px;
    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}

/* Full-width input fields */
input[type=text], input[type=password] ,input[type=number]{
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    border: none;
    background: #f1f1f1;
}
input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}
/* Set a style for the submit button */
.btn {
    background-color: #4CAF50;
    color: white;
    padding: 5px 5px;
    border: none;
    cursor: pointer;
    width: 50%;
    opacity: 0.9;
}
.btn:hover {
    opacity: 1;
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
<div class="bg-img">
<form action="AssetController" method=post> 
  <table align="center" id="AddAsset">
<h1 align="center">Add New Asset</h1>
<tr><td>Asset Id</td><td> <input type="number" placeholder="Enter assetid" name="assetid" pattern="[1-9][0-9]{3,3}" required></td></tr>
<tr><td>Asset Name</td><td><input type="text"  placeholder="Enter asset name" name="assetname" pattern="[A-Z][A-Za-z ]{2,25}" required></td></tr>
<tr><td>Asset Description</td><td><textarea name="assetdes" rows="5" cols="26"></textarea></td></tr>
<tr><td>Upload Image:</td><td><input type=file name="assetimage"></td></tr>
<tr><td>Quantity</td><td><input type="number" placeholder="Enter quantity" name="quantity" min=1 max=100 required></td></tr>
<tr><td>Status</td><td><select name="status"><option>AVAILABLE</option><option>NONAVAILABLE</option></select></br></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<button type="reset" class="btn">Reset</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn" name="action" value="addnewasset">Submit</td></tr>
</table>
 </form>
</div>
</body>
</html>