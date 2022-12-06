<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<c:import url="admin-navbar.jsp"/>
    <div class="admin-page-container">
        <div class="admin-heading-container">
            <h2>Courses</h2>
            <a href="admin/courses/add" class="add-course-ref">Add course</a>
        </div>
        <div class="selection-form-container">
    <form>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="sort">Sort By</label>
                <select class="form-control" name="sort" id="sort">
                    <option selected value="none">None</option>
                    <option value="name">Name (A-Z)</option>
                    <option value="name_desc">Name (Z-A)</option>
                    <option value="duration">Duration (from shortest)</option>
                    <option value="duration_desc">Duration (from longest)</option>
                    <option value="students">Students enrolled (from least)</option>
                    <option value="students_desc">Students enrolled (from most)</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="teacher">Teacher</label>
                <select class="form-control" name="teacher" id="teacher">
                    <option selected value="any">Any</option>
                    <c:forEach items="${teachers}" var="teacher">
                        <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="topic">Topic</label>
                <select class="form-control" name="topic" id="topic">
                    <option selected value="any">Any</option>
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic.id}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Apply</button>
    </form>
        </div>
        <ul class="list-group">
            <div class="grid-container">
                <c:forEach items="${courses}" var="course">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h3 class="card-title">${course.key.name}</h3>
                            <p>Teacher: ${course.value.firstName} ${course.value.lastName}</p>
                            <c:if test="${course.value == null}">
                                <p>No teacher assigned</p>
                            </c:if>
                            <p>Start date: ${course.key.startDate}</p>
                            <p>End date: ${course.key.endDate}</p>
                            <div style="display: flex; align-items: center; justify-content: space-around">
                                <a href="admin/courses/edit/${course.key.id}" class="btn btn-light">Edit</a>
                                <form action="admin/courses/delete/${course.key.id}" method="POST">
                                    <input type="submit" class="btn btn-danger" value="Delete" style="margin-top: 15px">
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
