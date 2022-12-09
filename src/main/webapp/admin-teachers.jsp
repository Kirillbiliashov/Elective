<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Teachers</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<admin:navbar adminUrl="../" studentsUrl="students" teachersUrl="teachers" activeNavItem="teachers"/>
<div class="page-container">
    <div class="admin-teachers-header">
        <h2><fmt:message key="teachers" /></h2>
        <a href="teachers/register?lang=${param.lang}"><fmt:message key="register_teacher" /></a>
    </div>
    <ul class="list-group">
        <div class="grid-container teacher-grid-container">
            <c:forEach items="${teachers}" var="teacher">
                <div class="card teacher-card">
                    <div class="card-body">
                        <p><teacher:info target="${teacher}"/></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </ul>
</div>
</body>
</html>
