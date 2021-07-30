<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="false"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="validation.js"></script>

</head>
<style>
body, html {
    height: 100%;
   
}

.navbar-default{
  		background-color:#2f2f1e;
  	
  	}

* {
    box-sizing: border-box;
}

.bg-img {
  
    background-image: url("bc3.jpg");

    height: 100%;
     background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    width:100%;
}


.container {
 position: absolute;
    right: 0;
    margin: 20px;
    max-width: 300px;
    padding: 16px;
    background-color:white;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    border: none;
    background: #f1f1f1;
}


.btn {
    background-color: #4CAF50;
    color: white;
    padding: 16px 20px;
    border: none;
    width: 100%;
  
}

.btn:hover {
    opacity: 1;
}
</style>




<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">AssetManagement System</a>
    </div>
    <ul class="nav navbar-nav">
      <li class=""><a href="HomePage.html">Home</a></li>
     <!--  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li> -->
    </ul>
    </div>
    </nav>

<div class="bg-img">
<div class="container">
  		<form action="AssetController" method=post>
    <div class="center">
      <h1>Login</h1>
	  
			Enter UserName/ID:<input type=text name="UserName" pattern="[A-Z][A-Za-z]{2,}|[1-9][0-9]{5,5}" required/>
			Enter Password:<input type="password" name="password" required/>
		 <!-- <div class="form-group">
      <label for="sel1">Role</label>
      <select class="form-control" id="sel1" name="role">
        <option>ADMIN</option>
        <option>MANAGER</option>
      </select></div> -->
    
     
			<input type=submit value="login" class="btn btn-primary btn-sm" name="action"/>
		
			<!-- <a href="forgotpassword.html">forgot password?</a> -->
		
		<%String msg;
			if(request.getAttribute("error")==null){
				msg=" ";
			}
			else
			{
				msg="Please Enter valid name and password";
			}
		
		%>
		<font color=red><%=msg %></font>
		</div>
   

  </form>
</div>
</div>
</body>
</html>