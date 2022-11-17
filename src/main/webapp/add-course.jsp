<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Add course</h1>
<form method="post">
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" placeholder="Name" name="name">
  </div>
  <div class="form-group">
    <label for="duration">Duration</label>
    <input type="text" class="form-control" id="duration" placeholder="Duration(in hours)" name="duration">
  </div>
  <div class="form-group">
    <label for="startDate">Start date</label>
    <input type="date" class="form-control" id="startDate" placeholder="Start date" name="startDate">
  </div>
  <div class="form-group">
    <label for="topicSelect">Topic</label>
    <select class="form-control" id="topicSelect" name="topicId">
      <c:forEach items="${topics}" var="topic">
        <option value="${topic.id}">${topic.name}</option>
      </c:forEach>
    </select>
  </div>
  <div class="form-group">
    <label for="teacherSelect">Teacher</label>
    <select class="form-control" id="teacherSelect" name="teacherId">
      <c:forEach items="${teachers}" var="teacher">
        <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
      </c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-primary">Add</button>
</form>
</body>
</html>
