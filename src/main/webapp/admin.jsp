<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Admin main page</h1>
<div style="display: flex; justify-content: space-around; margin: 30px ">
    <div style="width: 300px">
        <h2>Courses</h2>
        <ul class="list-group">
            <c:forEach items="${courses}" var="course">
                <li class="list-group-item">
                    <h3>${course.key.name}</h3>
                    <br>
                    ${course.value.firstName} ${course.value.lastName}
                    <c:if test="${course.value == null}">
                        <p>No teacher assigned</p>
                    </c:if>
                    <a href="courses/edit/${course.key.id}" class="btn btn-light">Edit</a>
                    <form action="courses/delete/${course.key.id}" method="POST">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </form>
                </li>

            </c:forEach>
        </ul>
        <a href="courses/add">Add course</a>
    </div>
    <div style="width: 300px">
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
    </div>
    <div style="width: 300px">
        <h2>Teachers</h2>
        <ul class="list-group">
            <c:forEach items="${teachers}" var="teacher">
                <li class="list-group-item">
                        ${teacher.login}
                    (${teacher.firstName} ${teacher.lastName})
                </li>
            </c:forEach>
        </ul>
        <a href="teachers/register">Register teacher</a>
    </div>
</div>
</body>
</html>
