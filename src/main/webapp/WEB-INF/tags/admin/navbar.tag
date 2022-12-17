<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="element" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="activeNavItem" %>
<%@ attribute name="teachersUrl" %>
<%@ attribute name="studentsUrl" %>
<%@ attribute name="adminUrl" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${activeNavItem eq "admin" ? "active" : ""}">
                <a class="nav-link" href="${adminUrl}?lang=${param.lang}"><fmt:message key="courses"/><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ${activeNavItem eq "students" ? "active" : ""}">
                <a class="nav-link" href="${studentsUrl}?lang=${param.lang}"><fmt:message key="students"/></a>
            </li>
            <li class="nav-item ${activeNavItem eq "teachers" ? "active" : ""}">
                <a class="nav-link" href="${teachersUrl}?lang=${param.lang}"><fmt:message key="teachers"/></a>
            </li>
        </ul>
    </div>
        <element:lang-dropdown/>
        <a href="/elective/logout?lang=${param.lang}" class="btn btn-primary"><fmt:message key="logout"/></a>
</nav>