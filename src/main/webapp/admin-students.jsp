<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Students</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="admin">Courses<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="students">Students</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="teachers">Teachers</a>
            </li>
        </ul>
    </div>
</nav>
<div style="margin: 30px">
    <h2>Students</h2>
    <ul class="list-group">
        <c:forEach items="${students}" var="student">
            <li class="list-group-item" style="margin: 10px">
                <div style="display: flex; justify-content: space-between">
                    <h6>${student.firstName} ${student.lastName} (${student.login})</h6>
                    <form action="students/changeBlock/${student.id}" method="POST">
                        <c:if test="${student.blocked}">
                            <input type="submit" class="btn btn-secondary" value="Unlock">
                        </c:if>
                        <c:if test="${!student.blocked}">
                            <input type="submit" class="btn btn-dark" value="Block">
                        </c:if>
                    </form>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
