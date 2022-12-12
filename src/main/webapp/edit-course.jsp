<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "teacher" uri = "/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="minDate" value="${now}" pattern="yyyy-MM-dd" />
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body style="margin: 30px">
<div class="dropdown-container"><element:lang-dropdown/></div>
<h1><fmt:message key="edit"/></h1>
<form method="post" action="?lang=${param.lang}">
  <div class="form-group">
    <label for="name"><fmt:message key="name"/></label>
    <input type="text" class="form-control" id="name" required  maxlength="50" placeholder="Name" name="name" value="${course.name}">
  </div>
  <div class="form-group">
    <label for="startDate"><fmt:message key="start_date"/></label>
    <input type="date" class="form-control" id="startDate" min="${minDate}" placeholder="Start date" name="startDate" value="${course.startDate}">
  </div>
  <div class="form-group">
    <label for="endDate"><fmt:message key="end_date"/></label>
    <input type="date" class="form-control" id="endDate" min="${minDate}" placeholder="End date" name="endDate" value="${course.endDate}">
  </div>
  <div class="form-group">
    <label for="topicSelect"><fmt:message key="topic"/></label>
  <select class="form-control" id="topicSelect" name="topicId">
    <c:forEach items="${topics}" var="topic">
      <option value="${topic.id}" <c:if test="${topic.id == course.topicId}">selected</c:if>>${topic.name}</option>
    </c:forEach>
  </select>
  </div>
  <div class="form-group">
    <label for="teacherSelect"><fmt:message key="teacher"/></label>
    <select class="form-control" id="teacherSelect" name="teacherId">
      <c:forEach items="${teachers}" var="teacher">
        <option value="${teacher.id}" <c:if test="${teacher.id == course.teacherId}">selected</c:if>>
            <teacher:fullName target="${teacher}"/>
        </option>
      </c:forEach>
    </select>
  </div>
  <input type="hidden" value="${course.id}" name="id"/>
  <button type="submit" class="btn btn-primary"><fmt:message key="edit"/></button>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
