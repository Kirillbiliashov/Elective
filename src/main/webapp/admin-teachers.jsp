<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Teachers</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="../">Courses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="students">Students</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="teachers">Teachers</a>
            </li>
        </ul>
    </div>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>
<div class="page-container">
    <div class="admin-teachers-header">
        <h2>Teachers</h2>
        <a href="teachers/register">Register teacher</a>
    </div>
    <ul class="list-group">
        <div class="grid-container teacher-grid-container">
            <c:forEach items="${teachers}" var="teacher">
                <div class="card teacher-card">
                    <div class="card-body">
                        <p>${teacher.firstName} ${teacher.lastName} (${teacher.login})</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </ul>
</div>
</body>
</html>
