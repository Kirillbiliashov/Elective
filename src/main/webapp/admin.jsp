<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.courses"/></title>
    <style>
        <%@include file="WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<admin:navbar adminUrl="" studentsUrl="admin/students" teachersUrl="admin/teachers" activeNavItem="admin"/>
<div class="page-container">
    <div class="admin-heading-container">
        <h2><fmt:message key="courses"/></h2>
        <a href="admin/courses/add" class="add-course-ref"><fmt:message key="add_course"/></a>
    </div>
    <%@include file="selection-form.jsp" %>
    <c:if test="${empty courses}">
        <h5 class="no-items-msg"><fmt:message key="no_courses"/></h5>
    </c:if>
    <c:if test="${not empty courses}">
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${courses}" var="course">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">${course.name}</h3>
                            <p><fmt:message key="teacher"/>: ${course.teacher}</p>
                            <p><fmt:message key="topic"/>: ${course.topic}</p>
                            <p><fmt:message key="course.start_date"/>: ${course.startDate}</p>
                            <p><fmt:message key="course.end_date"/>: ${course.endDate}</p>
                            <p><fmt:message key="course.students_count"/>: ${course.studentsCount}</p>
                            <%@include file="course-modal.jsp" %>
                            <div class="course-btn-container">
                                <a href="admin/courses/edit/${course.id}"
                                   class="btn btn-light"><fmt:message key="course.edit"/></a>
                                <form action="admin/courses/delete/${course.id}" method="POST">
                                    <input type="submit" class="btn btn-danger delete-course-btn"
                                           value="<fmt:message key="course.delete"/>">
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </c:if>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
