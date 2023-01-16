<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="acc" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.main"/></title>
    <style>
        <%@include file="WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="teacher-navbar-container">
        <h3><acc:info target="${account}"/></h3>
        <div class="teacher-navbar-buttons-container">
            <element:lang-dropdown/>
            <a href="/elective/logout" class="btn btn-primary"><fmt:message key="logout"/></a>
        </div>
    </div>
</nav>
<div class="page-container">
    <c:if test="${course ne null}">
        <h2><fmt:message key="course"/>: ${course.name}</h2>
        <p><fmt:message key="course.start_date"/>: ${course.startDate}</p>
        <p><fmt:message key="course.end_date"/>: ${course.endDate}</p>
        <div class="table-container">
            <c:if test="${not empty journals}">
                <table class="table table-bordered course-table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="student"/></th>
                        <th scope="col"><fmt:message key="grade"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${journals}" var="journal">
                        <tr>
                            <td>${journal.student}</td>
                            <td>
                                <c:if test="${journal.grade eq -1}">
                                    <c:if test="${course.endDate.after(currDate)}">
                                        <fmt:message key="course_not_finished"/>
                                    </c:if>
                                    <c:if test="${course.endDate.before(currDate)}">
                                        <form method="post" action="teacher/addGrade/${journal.id}?page=${page}&display=1">
                                            <div class="add-grade-input-container">
                                                <input type="number" min="0" max="100" name="grade" id="grade"/>
                                                <input type="submit" class="btn btn-primary btn-sm add-grade-btn"
                                                       value="<fmt:message key="add_grade"/>">
                                            </div>
                                        </form>
                                    </c:if>
                                </c:if>
                                <c:if test="${journal.grade ne -1}">
                                    ${journal.grade}
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty journals}">
                <h5 class="no-items-msg"><fmt:message key="no_students"/></h5>
            </c:if>
        </div>
        <nav>
            <ul class="pagination justify-content-center">
                <page:prev isCourse="true"/>
                <page:current isCourse="true"/>
                <page:next isCourse="true"/>
            </ul>
        </nav>
    </c:if>
    <c:if test="${course eq null}">
        <h3 class="no-items-msg"><fmt:message key="teacher.no_courses"/></h3>
    </c:if>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</html>