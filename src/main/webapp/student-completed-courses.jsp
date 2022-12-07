<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Student</h3>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="../">Available courses<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="registered_courses">Registered courses</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="courses_in_progress">Courses in progress</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="">Completed courses</a>
        </li>
    </ul>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>
<div class="page-container">
    <h2>Completed courses</h2>
    <c:if test="${!completedCourses.isEmpty()}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${completedCourses}" var="entry">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${entry.key.name}</h4>
                            <p>Start date: ${entry.key.startDate}</p>
                            <p>End date: ${entry.key.endDate}</p>
                            <p>Grade: ${entry.value.grade}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
    <c:if test="${completedCourses.isEmpty()}"><p>No completed courses</p></c:if>
</div>
</body>
</html>
