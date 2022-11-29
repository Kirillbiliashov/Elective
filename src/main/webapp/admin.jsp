<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="admin">Courses<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin/students">Students</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin/teachers">Teachers</a>
            </li>
        </ul>
    </div>
</nav>
    <div style="margin: 30px">
        <h2>Courses</h2>
        <div style="display: flex; justify-content: space-between; margin: 30px">
            <form method="get">
                <div style="display: flex; justify-content: center">
                    <div class="form-group">
                        <select class="form-control" name="sort">
                            <option selected value="none">Sort By</option>
                            <option value="name">Name (A-Z)</option>
                            <option value="name_reverse">Name (Z-A)</option>
                            <option value="duration_asc">Duration (from shortest)</option>
                            <option value="duration_desc">Duration (from longest)</option>
                            <option value="students_asc">Students enrolled (from least)</option>
                            <option value="students_desc">Students enrolled (from most)</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="topic">
                            <option selected value="none">Topic</option>>
                            <c:forEach items="${topics}" var="topic">
                                <option value="${topic.id}">${topic.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="teacher">
                            <option selected value="none">Teacher</option>
                            <c:forEach items="${teachers}" var="teacher">
                                <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Apply">
                </div>
            </form>
            <a href="courses/add">Add course</a>
        </div>
        <ul class="list-group">
            <c:forEach items="${courses}" var="course">
                <li class="list-group-item" style="margin: 10px">
                    <div style="display: flex; justify-content: space-between">
                        <div>
                            <h3>${course.key.name}</h3>
                            <p>${course.value.firstName} ${course.value.lastName}</p>
                            <c:if test="${course.value == null}">
                                <p>No teacher assigned</p>
                            </c:if>
                        </div>
                        <div style="display: flex">
                            <a href="admin/courses/edit/${course.key.id}" class="btn btn-light">Edit</a>
                            <form action="admin/courses/delete/${course.key.id}" method="POST">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </form>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
