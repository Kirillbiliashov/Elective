<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.11.2022
  Time: 10:25 AM
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
<div class="login-page-container">
    <h1 class="form-header">Login</h1>
    <div>
        <form method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username" name="login">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
            </div>
            <button type="submit" class="btn btn-primary submit-btn">Login</button>
        </form>
    </div>
    <a href="signup" class="signup-ref">Sign up as a student</a>
</div>

<c:if test="${errorMsg != null}">
    <div class="alert alert-warning" role="alert">
        ${errorMsg}
    </div>
</c:if>

</body>
</html>
