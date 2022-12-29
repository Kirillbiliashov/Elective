<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix = "teacher" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix = "ref" tagdir="/WEB-INF/tags/url" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.courses"/></title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<admin:navbar adminUrl="" studentsUrl="admin/students" teachersUrl="admin/teachers" activeNavItem="admin"/>
    <div class="page-container">
        <div class="admin-heading-container">
            <h2><fmt:message key="courses" /></h2>
            <a href="<ref:lang value="admin/courses/add"/>" class="add-course-ref"><fmt:message key="add_course" /></a>
        </div>
        <div class="selection-form-container">
    <form>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="sort"><fmt:message key="sort.sort_by" /></label>
                <select class="form-control" name="sort" id="sort">
                    <option selected value="none"><fmt:message key="sort.none" /></option>
                    <option value="name"><fmt:message key="sort.name_asc" /></option>
                    <option value="name_desc"><fmt:message key="sort.name_desc" /></option>
                    <option value="duration"><fmt:message key="sort.duration_asc" /></option>
                    <option value="duration_desc"><fmt:message key="sort.duration_desc"/></option>
                    <option value="students"><fmt:message key="sort.students_asc" /></option>
                    <option value="students_desc"><fmt:message key="sort.students_desc" /></option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="teacher"><fmt:message key="teacher"/></label>
                <select class="form-control" name="teacher" id="teacher">
                    <option selected value="Any"><fmt:message key="teacher.any" /></option>
                    <c:forEach items="${teachers}" var="teacher">
                        <option value="<teacher:fullName target="${teacher}"/>"><teacher:fullName target="${teacher}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="topic"><fmt:message key="topic" /></label>
                <select class="form-control" name="topic" id="topic">
                    <option selected value="Any"><fmt:message key="topic.any"/></option>
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic.name}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <input type="hidden" name="lang" value="${param.lang}" />
        <button type="submit" class="btn btn-primary"><fmt:message key="selection.apply"/></button>
    </form>
        </div>
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${courses}" var="course">
                    <div class="card course-card">
                        <div class="card-body">
                            <h3 class="card-title">${course.name}</h3>
                            <p><fmt:message key="teacher"/>: ${course.teacher}</p>
                            <p><fmt:message key="topic"/>: ${course.topic}</p>
                            <p><fmt:message key="course.start_date"/>: ${course.startDate}</p>
                            <p><fmt:message key="course.end_date"/>: ${course.endDate}</p>
                            <div class="course-btn-container">
                                <a href="<ref:lang value="admin/courses/edit/${course.id}"/>"
                                   class="btn btn-light"><fmt:message key="course.edit"/></a>
                                <form action="<ref:lang value="admin/courses/delete/${course.id}"/>" method="POST">
                                    <input type="submit" class="btn btn-danger delete-course-btn"
                                           value="<fmt:message key="course.delete"/>">
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
