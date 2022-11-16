<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15.11.2022
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Edit course</h1>
<form method="post">
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" placeholder="Name" name="name" value="${course.name}">
  </div>
  <div class="form-group">
    <label for="duration">Duration</label>
    <input type="text" class="form-control" id="duration" placeholder="Duration(in hours)" name="duration" value="${course.duration}">
  </div>
  <div class="form-group">
    <label for="startDate">Start date</label>
    <input type="date" class="form-control" id="startDate" placeholder="Start date" name="startDate" value="${course.startDate}">
  </div>
  <div class="form-group">
  <select class="form-control" id="topicSelect" name="topicId">
    <c:forEach items="${topics}" var="topic">
      <option value="${topic.id}" selected ="${topic.id == course.topicId}">${topic.name}</option>
    </c:forEach>
  </select>
  </div>
  <div class="form-group">
    <select class="form-control" id="teacherSelect" name="teacherId">
      <c:forEach items="${teachers}" var="teacher">
        <option value="${teacher.id}" selected="${course.teacherId == teacher.id}">
            ${teacher.firstName} ${teacher.lastName}
        </option>
      </c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-primary">Edit</button>
</form>
</body>
</html>
