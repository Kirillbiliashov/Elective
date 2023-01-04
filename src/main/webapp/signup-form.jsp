<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix = "ref" tagdir="/WEB-INF/tags/url" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="title.signup"/></title>
    <style><%@include file="style.css" %></style>
</head>
<body>
<div class="dropdown-container"><element:lang-dropdown/></div>
<div class="post-form">
    <h1 class="form-header"><fmt:message key="user.signup" /></h1>
    <form method="post" action="<ref:lang value=""/>" onsubmit="return validateForm()">
        <form:firstName/>
        <form:lastName/>
        <form:username/>
        <form:email/>
        <form:password/>
        <form:confirm-password/>
        <input type="hidden" name="role" value="Student">
        <form:button key="user.signup"/>
    </form>
</div>
</body>
<script type="application/javascript"><%@include file="WEB-INF/js/passwordValidation.js" %></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
