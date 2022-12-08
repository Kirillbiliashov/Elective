<%@ attribute name="activeNavItem" %>
<%@ attribute name="completedCoursesUrl" %>
<%@ attribute name="coursesInProgressUrl" %>
<%@ attribute name="registeredCoursesUrl" %>
<%@ attribute name="studentUrl" %>
<%@ taglib prefix="student" uri="/WEB-INF/tld/account.tld" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3><student:info target="${account}"/></h3>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item ${activeNavItem.equals("student") ? "active" : ""}">
            <a class="nav-link" href=${studentUrl}>Available courses<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item ${activeNavItem.equals("registeredCourses") ? "active" : ""}">
            <a class="nav-link" href=${registeredCoursesUrl}>Registered courses</a>
        </li>
        <li class="nav-item ${activeNavItem.equals("coursesInProgress") ? "active" : ""}">
            <a class="nav-link" href=${coursesInProgressUrl}>Courses in progress</a>
        </li>
        <li class="nav-item ${activeNavItem.equals("completedCourses") ? "active" : ""}">
            <a class="nav-link" href=${completedCoursesUrl}>Completed courses</a>
        </li>
    </ul>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>