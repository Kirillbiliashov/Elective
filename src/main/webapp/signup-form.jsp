<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.11.2022
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<div class="post-form">
    <h1 class="form-header">Sign Up</h1>
    <form method="post">
        <div class="form-group">
            <label for="fname">First Name</label>
            <input type="text" name="firstName" id="fname" class="form-control" placeholder="First Name"/>
        </div>
        <div class="form-group">
            <label for="lname">Last Name</label>
            <input type="text" name="lastName" id="lname" class="form-control" placeholder="Last Name"/>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="login" id="username" class="form-control" placeholder="Username"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password"/>
        </div>
        <input type="hidden" name="roleId" value="${roleId}">
            <button type="submit" class="btn btn-primary submit-btn">Submit</button>
    </form>
</div>

</body>
</html>
