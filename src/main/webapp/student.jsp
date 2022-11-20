<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Student main page</h1>
<c:if test="${!unregisteredCourses.isEmpty()}">
<div style="margin: 30px">
    <h3>Available Courses</h3>
        <ul class="list-group">
            <c:forEach items="${unregisteredCourses}" var="course">
                <h4>${course.name}</h4>
                <p>Start Date: ${course.startDate}</p>
                <form action="courses/enroll/${course.id}" method="post">
                    <input type="submit" class="btn btn-primary" value="Enroll">
                </form>
            </c:forEach>
        </ul>
</div>
</c:if>
<div style="display: flex; justify-content: space-around; margin: 30px">
    <div>
        <h3>Registered courses</h3>
        <ul class="list-group">
            <c:forEach items="${registeredCourses}" var="entry">
                <li class="list-group-item">
                    <h4>${entry.key.name}</h4>
                    <p>Start date: ${entry.key.startDate}</p>
                    <p>Registration date: ${entry.value.enrollmentDate}</p>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <h3>Courses In Progress</h3>
        <ul class="list-group">
            <c:forEach items="${coursesInProgress.keySet()}" var="courseInProgress">
                <li class="list-group-item">
                    <h4>${courseInProgress.name}</h4>
                    <p>Start date: ${courseInProgress.startDate}</p>
                    <p>End date: ${courseInProgress.endDate}</p>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <h3>Completed courses</h3>
        <ul class="list-group">
            <c:forEach items="${completedCourses}" var="entry">
                <li class="list-group-item">
                    <h4>${entry.key.name}</h4>
                    <p>End date: ${entry.key.endDate}</p>
                    <p>Grade: ${entry.value.grade}</p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
