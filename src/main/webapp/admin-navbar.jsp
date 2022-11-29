<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.11.2022
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="">Courses<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin/students">Students</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin/teachers">Teachers</a>
            </li>
        </ul>
    </div>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>
</body>
</html>
