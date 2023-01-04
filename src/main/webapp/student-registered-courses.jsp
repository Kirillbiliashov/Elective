<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="student" tagdir="/WEB-INF/tags/student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="student.registered_courses"/></title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<student:navbar studentUrl="../student" registeredCoursesUrl=""
                coursesInProgressUrl="courses_in_progress" completedCoursesUrl="completed_courses"
                activeNavItem="registeredCourses"/>
<div class="page-container">
    <h2><fmt:message key="student.registered_courses"/></h2>
    <c:if test="${not empty registeredCourses}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${registeredCourses}" var="course">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h4>${course.name}</h4>
                            <p><fmt:message key="topic"/>: ${course.topic}</p>
                            <p><fmt:message key="teacher"/>: ${course.teacher}</p>
                            <p><fmt:message key="course.start_date"/>: ${course.startDate}</p>
                            <p><fmt:message key="course.end_date"/>: ${course.endDate}</p>
                            <p>Students enrolled: ${course.studentsCount}</p>
                            <button type="button" class="btn btn-link description-btn" data-toggle="modal" data-target="#descModal">
                                <fmt:message key="course.description"/>
                            </button>
                            <div class="modal fade" id="descModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">${course.name}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                                ${course.description}
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Ok</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
    <c:if test="${empty registeredCourses}"><p><fmt:message key="student.no_registered_courses"/></p></c:if>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
