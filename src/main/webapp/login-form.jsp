<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.login"/></title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<div class="dropdown-container">
    <element:lang-dropdown/>
</div>
<div class="login-page-container">
    <h1 class="form-header"><fmt:message key="user.login"/></h1>
    <div>
        <form method="post">
            <form:login/>
            <form:password/>
            <form:button key="user.login" />
        </form>
    </div>
    <div class="form-btn-container">
        <a href="signup"><fmt:message key="user.signup_ref"/></a>
    </div>
    <c:if test="${loginFailed}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="login_fail"/>
        </div>
    </c:if>
    <c:if test="${accountBlocked}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="account_blocked"/>
        </div>
    </c:if>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
