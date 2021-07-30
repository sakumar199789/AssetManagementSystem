<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="true" import="java.util.*,com.capgemini.asset.bean.*"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AssetManagement System</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function details(){
		var empId=document.getElementById("empId").value;
		<c:forEach items="${sessionScope.employeeId}" var="id">
			if(empId==${id.empno}){
				document.getElementById("ename").value="${id.empName}";
				document.getElementById("deptId").value="${id.deptId}";
		/* 		document.getElementById("deptId").value='${id.deptId}''; */
			}	
</c:forEach>
		
		
		
	
}
</script>
<style>
.container{
backgroung-color:blue;

}
.btn {
    background-color: #4CAF50;
    color: white;
    padding: 16px 20px;
    border: none;
}

.btn:hover {
    opacity: 1;
}

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
      <li><a href="AssetController?action=RaiseRequest">Raise a Request</a></li> 
        <li><a href="AssetController?action=viewrequeststatus">View Request Status</a></li> 
        </ul>
        
    <ul class="nav navbar-nav navbar-right">
   <!--    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
      <li><a href="AssetController?action=logout" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	</ul>
    
    
  </div>
</nav>

<div class="container">
  <center><h2>Request Form</h2></center>
  <form class="form-horizontal" action="AssetController" method=POST>
  	   <div class="form-group">        
       <label class="control-label col-sm-4 " for="asset">Employee Id:</label>
      <div class="col-sm-3">          
        <select name="empid" id="empId" onClick="details()">
			<c:forEach items="${sessionScope.employeeId}" var="id">
					<option value='${id.empno}'>${id.empno}</option>
			</c:forEach>
			</select><br/>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-4" for="employee">Employee Name:</label>
      <div class="col-sm-3">
        <input type="text" class="form-control" placeholder="Enter employee name" name="empName" id="ename" required readonly>
      </div>
    </div>
    <div class="form-group">        
       <label class="control-label col-sm-4 " for="deptid">Department Id:</label>
      <div class="col-sm-3">          
        <input type="number" class="form-control" placeholder="Enter department id" name="deptid" id="deptId" required readonly>
      </div>
    </div>
      <div class="form-group">        
       <label class="control-label col-sm-4 " for="quantity">Quantity:</label>
      <div class="col-sm-3">          
        <input type="number" class="form-control" placeholder="Enter Quantity" name="quantity" min=1 max=100 required>
      </div>
    </div>
     <div class="form-group">        
       <label class="control-label col-sm-4 " for="asset">Select Asset:</label>
      <div class="col-sm-3">          
        <select name="assetname">
			<c:forEach items="${sessionScope.assetlist}" var="id">
					<option value='${id.assetName}'>${id.assetName}</option>
					
			</c:forEach>
			</select><br/>
      </div>
    </div>
       <div class="form-group">        
       <label class="control-label col-sm-4" for="assetpurpose">Asset Purpose:</label>
      <div class="col-sm-6">          
       <textarea rows="5" cols="35" name="assetpurpose"></textarea>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <input type=reset value="reset" class="btn btn-primary btn-sm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type=submit name=action value="RaiseRequest" class="btn btn-primary btn-sm" />
      </div>
    </div>
  </form>
</div>
</body>
</html>
