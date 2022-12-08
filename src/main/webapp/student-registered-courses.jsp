<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="student" tagdir="/WEB-INF/tags/student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<student:navbar studentUrl="../" registeredCoursesUrl=""
                coursesInProgressUrl="courses_in_progress" completedCoursesUrl="completed_courses"
                activeNavItem="registeredCourses"/>
<div class="page-container">
    <h2><fmt:message key="registered_courses"/></h2>
    <c:if test="${!registeredCourses.isEmpty()}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${registeredCourses}" var="entry">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${entry.key.name}</h4>
                            <p><fmt:message key="registration_date"/>: ${entry.value.enrollmentDate}</p>
                            <p><fmt:message key="start_date"/>: ${entry.key.startDate}</p>
                            <p><fmt:message key="end_date"/>: ${entry.key.endDate}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
    <c:if test="${registeredCourses.isEmpty()}"><p><fmt:message key="no_registered_courses"/></p></c:if>
</div>
</body>
</html>
