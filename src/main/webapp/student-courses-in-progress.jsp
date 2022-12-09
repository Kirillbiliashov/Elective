<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="student" tagdir="/WEB-INF/tags/student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<student:navbar studentUrl="../" registeredCoursesUrl="registered_courses"
                coursesInProgressUrl="" completedCoursesUrl="completed_courses"
                activeNavItem="coursesInProgress"/>
<div class="page-container">
    <h2><fmt:message key="courses_in_progress"/></h2>
<c:if test="${!coursesInProgress.isEmpty()}">
    <ul class="list-group">
        <div class="grid-container">
                <c:forEach items="${coursesInProgress}" var="course">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${course.name}</h4>
                            <p><fmt:message key="start_date"/>: ${course.startDate}</p>
                            <p><fmt:message key="end_date"/>: ${course.endDate}</p>
                        </div>
                    </div>
                </c:forEach>
        </div>
    </ul>
    </c:if>
    <c:if test="${coursesInProgress.isEmpty()}"><p><fmt:message key="no_courses_in_progress"/></p></c:if>
</div>
</body>
</html>
