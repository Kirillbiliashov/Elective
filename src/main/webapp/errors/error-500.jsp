<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="title.server_error"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="../style.css" %>
    </style>
</head>
<body>
<div class="page-container">
    <h1><fmt:message key="error.server_error"/></h1>
    <a href="${homeUrl}?lang=${param.lang}"><fmt:message key="error.back"/></a>
</div>
</body>
</html>
