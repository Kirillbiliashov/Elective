<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Students</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<admin:navbar adminUrl="../" studentsUrl="students" teachersUrl="teachers" activeNavItem="students"/>
<div class="page-container">
    <h2>Students</h2>
    <ul class="list-group">
        <div class="grid-container">
            <c:forEach items="${students}" var="student">
                <div class="card student-card">
                    <div class="card-body">
                        <h5>${student.firstName} ${student.lastName} (${student.login})</h5>
                        <div style="align-items: center; justify-content: center; display: flex">
                            <form action="students/changeBlock/${student.id}" method="POST">
                                <c:if test="${student.blocked}">
                                    <input type="submit" class="btn btn-secondary" value="Unlock">
                                </c:if>
                                <c:if test="${!student.blocked}">
                                    <input type="submit" class="btn btn-dark" value="Block">
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </ul>
</div>
</body>
</html>
