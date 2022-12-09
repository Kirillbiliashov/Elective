<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="student" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Students</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<admin:navbar adminUrl="../" studentsUrl="students" teachersUrl="teachers" activeNavItem="students"/>
<div class="page-container">
    <h2><fmt:message key="students" /></h2>
    <ul class="list-group">
        <div class="grid-container">
            <c:forEach items="${students}" var="student">
                <div class="card student-card">
                    <div class="card-body">
                        <h5><student:info target="${student}"/></h5>
                        <div style="align-items: center; justify-content: center; display: flex">
                            <form action="students/changeBlock/${student.id}?lang=${param.lang}" method="POST">
                                <c:if test="${student.blocked}">
                                    <input type="submit" class="btn btn-secondary" value="<fmt:message key="unlock"/>">
                                </c:if>
                                <c:if test="${!student.blocked}">
                                    <input type="submit" class="btn btn-dark" value="<fmt:message key="block"/>">
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </ul>
</div>
</body>
</html>
