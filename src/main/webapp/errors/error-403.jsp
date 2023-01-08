<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.access_forbidden"/></title>
    <style>
        <%@include file="../WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<div class="page-container">
    <h1><fmt:message key="error.access_forbidden"/></h1>
    <a href="/elective/${homeUrl}"><fmt:message key="error.back"/></a>
</div>
</body>
</html>
