<%@ attribute name="activeNavItem" %>
<%@ attribute name="completedCoursesUrl" %>
<%@ attribute name="coursesInProgressUrl" %>
<%@ attribute name="registeredCoursesUrl" %>
<%@ attribute name="studentUrl" %>
<%@ taglib prefix="student" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix = "ref" tagdir="/WEB-INF/tags/url" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3><student:info target="${account}"/></h3>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item ${activeNavItem eq "student" ? "active" : ""}">
            <a class="nav-link" href="<ref:lang value="${studentUrl}"/>"><fmt:message key="student.available_courses" /><span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item ${activeNavItem eq "registeredCourses" ? "active" : ""}">
            <a class="nav-link" href="<ref:lang value="${registeredCoursesUrl}"/>"><fmt:message key="student.registered_courses" /></a>
        </li>
        <li class="nav-item ${activeNavItem eq "coursesInProgress" ? "active" : ""}">
            <a class="nav-link" href="<ref:lang value="${coursesInProgressUrl}"/>"><fmt:message key="student.courses_in_progress" /></a>
        </li>
        <li class="nav-item ${activeNavItem eq "completedCourses" ? "active" : ""}">
            <a class="nav-link" href="<ref:lang value="${completedCoursesUrl}"/>"><fmt:message key="student.completed_courses" /></a>
        </li>
    </ul>
        <element:lang-dropdown/>
    <a href="<ref:lang value="/elective/logout"/>" class="btn btn-primary"><fmt:message key="logout"/></a>
</nav>