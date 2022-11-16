<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<h1>Student main page</h1>
<div>
    <h2>Courses</h2>
    <ul class="list-group">
        <c:forEach items="${courseEnrolled}" var="entry">
            <li class="list-group-item">
                <h3>${entry.key.name}</h3>
                <c:if test="${!entry.value}">
                    <form action="courses/enroll/${entry.key.id}" method="post">
                        <input type="submit" class="btn btn-primary" value="Enroll">
                    </form>
                </c:if>
                <c:if test="${entry.value}">
                    <button class="btn btn-block disabled">Enrolled</button>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
