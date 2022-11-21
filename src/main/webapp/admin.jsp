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
        <div style="display: flex;">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                    Sort By
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="admin?sort=name">Name (A-Z)</a>
                    <a class="dropdown-item" href="admin?sort=name_reverse">Name (Z-A)</a>
                    <a class="dropdown-item" href="admin?sort=duration_asc">Duration (from shortest)</a>
                    <a class="dropdown-item" href="admin?sort=duration_desc">Duration (from longest)</a>
                    <a class="dropdown-item" href="admin?sort=students_asc">Students enrolled (from least)</a>
                    <a class="dropdown-item" href="admin?sort=students_desc">Students enrolled (from most)</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                    Topic
                </button>
                <div class="dropdown-menu">
                    <c:forEach items="${topics}" var="topic">
                        <a class="dropdown-item" href="admin?topic=${topic.id}">${topic.name}</a>
                    </c:forEach>
                </div>
            </div>
        </div>


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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
