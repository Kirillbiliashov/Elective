<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="student" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.students"/></title>
    <style>
        <%@include file="WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<admin:navbar adminUrl="../admin" studentsUrl="" teachersUrl="teachers" activeNavItem="students"/>
<div class="page-container">
    <h2><fmt:message key="students"/></h2>
    <jsp:include page="display-form.jsp"/>
    <c:if test="${not empty students}">
        <div class="table-container">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="student.name"/></th>
                    <th scope="col"><fmt:message key="user.username"/></th>
                    <th scope="col"><fmt:message key="user.email"/></th>
                    <th scope="col"><fmt:message key="student.status"/></th>
                    <th><fmt:message key="student.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.fullName}</td>
                        <td>${student.username}</td>
                        <td>${student.email}</td>
                        <td>
                            <c:if test="${student.blocked}"><fmt:message key="student.blocked"/></c:if>
                            <c:if test="${!student.blocked}"><fmt:message key="student.active"/></c:if>
                        </td>
                        <td>
                            <form action="students/changeBlock/${student.id}?page=${param.page}&display=${param.display}"
                                  method="POST">
                                <c:if test="${student.blocked}">
                                    <input type="submit" class="btn btn-secondary"
                                           value="<fmt:message key="students.unlock"/>">
                                </c:if>
                                <c:if test="${!student.blocked}">
                                    <input type="submit" class="btn btn-dark"
                                           value="<fmt:message key="students.block"/>">
                                </c:if>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="pagination.jsp"/>
    </c:if>
    <c:if test="${empty students}">
        <h5><fmt:message key="no_students"/></h5>
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
