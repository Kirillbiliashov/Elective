<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="student" tagdir="/WEB-INF/tags/student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="student.available_courses"/></title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<student:navbar studentUrl="" registeredCoursesUrl="student/registered_courses"
                coursesInProgressUrl="student/courses_in_progress"
                completedCoursesUrl="student/completed_courses" activeNavItem="student"/>
<div class="page-container">
    <h2><fmt:message key="student.available_courses"/></h2>
    <c:if test="${not empty availableCourses}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${availableCourses}" var="course">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${course.name}</h4>
                            <p><fmt:message key="course.start_date" />: ${course.startDate}</p>
                            <p><fmt:message key="course.end_date" />: ${course.endDate}</p>
                            <form action="student/courses/enroll/${course.id}?lang=${param.lang}" method="post">
                                <input type="submit" class="btn btn-primary" value="<fmt:message key="student.enroll" />">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
    <c:if test="${empty availableCourses}"><p><fmt:message key="student.no_available_courses"/></p></c:if>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
