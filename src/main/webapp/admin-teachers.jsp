<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Teachers</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="../admin">Courses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="students">Students</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="teachers">Teachers</a>
            </li>
        </ul>
    </div>
</nav>
<div style="margin: 30px">
    <div style="display: flex; width: 300px; justify-content: space-between; align-items: center">
        <h2>Teachers</h2>
        <a href="teachers/register">Register teacher</a>
    </div>
    <ul class="list-group">
        <c:forEach items="${teachers}" var="teacher">
            <li class="list-group-item" style="margin: 10px">
                <p>${teacher.firstName} ${teacher.lastName} (${teacher.login})</p>
            </li>
        </c:forEach>
    </ul>

</div>
</body>
</html>
