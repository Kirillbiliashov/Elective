<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" tagdir="/WEB-INF/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<div class="login-page-container">
    <h1 class="form-header">Login</h1>
    <div>
        <form method="post">
            <form:username/>
            <form:password/>
            <form:button text="Login"/>
        </form>
    </div>
    <a href="signup" class="signup-ref">Sign up as a student</a>
</div>

<c:if test="${errorMsg != null}">
    <div class="alert alert-warning" role="alert">
        ${errorMsg}
    </div>
</c:if>

</body>
</html>
