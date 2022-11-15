<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.11.2022
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Admin main page</h1>
<h2>Courses</h2>
<ul class="list-group">
    <c:forEach items="${courses}" var="course">
        <li class="list-group-item">${course.name}
            <a href="courses/edit/${course.id}" class="btn btn-light">Edit</a>
            <form action="courses/delete/${course.id}" method="POST">
                <input type="submit" class="btn btn-danger" value="Delete">
            </form>
        </li>
    </c:forEach>
</ul>
<a href="courses/add">Add course</a>
<h2>Students</h2>
<ul class="list-group">
    <c:forEach items="${students}" var="student">
        <li class="list-group-item">
            ${student.login}
            (${student.firstName} ${student.lastName})
                <form action="students/changeBlock/${student.id}" method="POST">
                    <c:if test="${student.blocked}">
                        <input type="submit" class="btn btn-secondary" value="Unlock">
                    </c:if>
                    <c:if test="${!student.blocked}">
                        <input type="submit" class="btn btn-dark" value="Block">
                    </c:if>
                </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
