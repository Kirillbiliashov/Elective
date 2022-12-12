<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form/course" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="minDate" value="${now}" pattern="yyyy-MM-dd" />
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
  <style><%@include file="style.css" %></style>
</head>
<body>
<div class="dropdown-container"><element:lang-dropdown/></div>
<div class="add-course-form">
  <h1><fmt:message key="add_course" /></h1>
  <form method="post" action="?lang=${param.lang}" onsubmit="return validateForm()" name="addCourse">
    <form:name/>
    <form:startDate/>
    <form:endDate/>
    <form:topic/>
    <form:teacher/>
    <div class="form-btn-container">
      <button type="submit" class="btn btn-primary form-btn"><fmt:message key="course.add" /></button>
    </div>
  </form>
</div>

</body>
<script type="application/javascript"><%@include file="WEB-INF/js/datesValidation.js" %></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
