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
        <h3>${account.login}</h3>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="">Available courses<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="student/registered_courses">Registered courses</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="student/courses_in_progress">Courses in progress</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="student/completed_courses">Completed courses</a>
        </li>
    </ul>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>
<div class="page-container">
    <h2>Available courses</h2>
    <c:if test="${!availableCourses.isEmpty()}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${availableCourses}" var="course">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${course.name}</h4>
                            <p>Start Date: ${course.startDate}</p>
                            <p>End Date: ${course.endDate}</p>
                            <form action="student/courses/enroll/${course.id}" method="post">
                                <input type="submit" class="btn btn-primary" value="Enroll">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
    <c:if test="${availableCourses.isEmpty()}"><p>No available courses</p></c:if>
</div>
</body>
</html>
