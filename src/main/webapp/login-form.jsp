<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.11.2022
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
  <input type="text" name="login"/>
  <input type="password" name="password"/>
    <input type="submit" value="Login"/>
    <c:if test="${errorMsg != null}">
        <p>${errorMsg}</p>
        </c:if>
</form>
<a href="signup">Sign up as a student</a>
</body>
</html>
