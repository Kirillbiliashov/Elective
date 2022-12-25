<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <title><fmt:message key="error.page_not_found"/></title>
  <style>
    <%@include file="../style.css" %>
  </style>
</head>
<body>
<div class="page-container">
  <h1><fmt:message key="error.page_not_found"/></h1>
  <a href="/elective/${homeUrl}?lang=${param.lang}"><fmt:message key="error.back"/></a>
</div>
</body>
</html>
