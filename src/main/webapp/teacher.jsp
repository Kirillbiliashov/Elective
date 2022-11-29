<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body style="margin: 20px">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Teacher</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent"/>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>

    <div style="margin: 30px;">
        <h2>Course: ${journal.key.name}</h2>
        <table class="table" style="margin: 30px">
            <thead>
            <tr>
                <th scope="col">Student</th>
                <th scope="col">Grade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${journal.value}" var="journalData">
                <tr>
                    <td>${journalData.value.firstName} ${journalData.value.lastName}</td>
                    <c:if test="${journalData.key.grade == -1}">
                        <c:if test="${journal.key.endDate.after(currDate)}">
                            <td>Course hasn't finished yet</td>
                        </c:if>
                        <c:if test="${journal.key.endDate.before(currDate)}">
                            <td>
                                <form method="post" action="teacher/journal/addGrade/${journalData.key.id}">
                                    <input type="number" min="0" max="100" name="grade" id="grade"/>
                                    <input type="submit" class="btn btn-primary" value="Add grade">
                                </form>
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${journalData.key.grade != -1}">
                        <td>${journalData.key.grade}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<c:import url="journal-pagination.jsp"/>
</body>
</html>