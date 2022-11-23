<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div style="margin: 20px">
    <h2>Courses</h2>
    <div style="display: flex; justify-content: space-around">
            <div style="margin: 15px">
                <h3>Available</h3>
            <c:if test="${!unregisteredCourses.isEmpty()}">
                <ul class="list-group">
                    <c:forEach items="${unregisteredCourses}" var="course">
                        <li class="list-group-item" style="margin: 10px">
                            <h4>${course.name}</h4>
                            <p>Start Date: ${course.startDate}</p>
                            <form action="courses/enroll/${course.id}" method="post">
                                <input type="submit" class="btn btn-primary" value="Enroll">
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
                <c:if test="${unregisteredCourses.isEmpty()}"><p>No available courses</p></c:if>
            </div>
            <div style="margin: 15px">
                <h3>Registered</h3>
                <c:if test="${!registeredCourses.isEmpty()}">
                    <ul class="list-group">
                        <c:forEach items="${registeredCourses}" var="entry">
                            <li class="list-group-item" style="margin: 10px">
                                <h4>${entry.key.name}</h4>
                                <p>Start date: ${entry.key.startDate}</p>
                                <p>Registration date: ${entry.value.enrollmentDate}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${registeredCourses.isEmpty()}"><p>No registered courses</p></c:if>
            </div>
                <div style="margin: 15px">
                    <h3>In progress</h3>
                <c:if test="${!coursesInProgress.isEmpty()}">
                    <ul class="list-group">
                        <c:forEach items="${coursesInProgress.keySet()}" var="courseInProgress">
                            <li class="list-group-item" style="margin: 10px">
                                <h4>${courseInProgress.name}</h4>
                                <p>Start date: ${courseInProgress.startDate}</p>
                                <p>End date: ${courseInProgress.endDate}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                    <c:if test="${coursesInProgress.isEmpty()}"><p>No courses in progress</p></c:if>
                </div>
            <div style="margin: 15px">
                <h3>Completed</h3>
                <c:if test="${!completedCourses.isEmpty()}">
                    <ul class="list-group">
                        <c:forEach items="${completedCourses}" var="entry">
                            <li class="list-group-item" style="margin: 10px">
                                <h4>${entry.key.name}</h4>
                                <p>End date: ${entry.key.endDate}</p>
                                <p>Grade: ${entry.value.grade}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${completedCourses.isEmpty()}"><p>No completed courses</p></c:if>
            </div>
    </div>
</div>


</body>
</html>
