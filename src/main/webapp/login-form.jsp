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
</head>
<body>
<%--<form method="post">--%>
<%--  <input type="text" name="login"/>--%>
<%--  <input type="password" name="password"/>--%>
<%--    <input type="submit" value="Login"/>--%>
<%--    <c:if test="${errorMsg != null}">--%>
<%--        <p>${errorMsg}</p>--%>
<%--        </c:if>--%>
<%--</form>--%>
<form method="post">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" placeholder="Username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<div class="form-group">
    <label for="exampleFormControlSelect1">I am: </label>
    <select class="form-control" id="exampleFormControlSelect1">
        <option>Student</option>
        <option>Teacher</option>
        <option>Admin</option>
    </select>
</div>
<a href="signup">Sign up as a student</a>
</body>
</html>
