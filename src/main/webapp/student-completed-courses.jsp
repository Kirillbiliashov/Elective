<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="student" tagdir="/WEB-INF/tags/student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<student:navbar studentUrl="../" registeredCoursesUrl="registered_courses"
                coursesInProgressUrl="courses_in_progress" completedCoursesUrl=""
                activeNavItem="completedCourses"/>
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
