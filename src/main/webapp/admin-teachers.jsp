<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
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
        <h2>Teachers</h2>
        <a href="teachers/register">Register teacher</a>
    </div>
    <ul class="list-group">
        <div class="grid-container teacher-grid-container">
            <c:forEach items="${teachers}" var="teacher">
                <div class="card teacher-card">
                    <div class="card-body">
                        <p>${teacher.firstName} ${teacher.lastName} (${teacher.login})</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </ul>
</div>
</body>
</html>
