<%@ attribute name="activeNavItem" %>
<%@ attribute name="teachersUrl" %>
<%@ attribute name="studentsUrl" %>
<%@ attribute name="adminUrl" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.11.2022
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h3>Admin</h3>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${activeNavItem.equals("admin") ? "active" : ""}">
                <a class="nav-link" href=${adminUrl}>Courses<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ${activeNavItem.equals("students") ? "active" : ""}">
                <a class="nav-link" href=${studentsUrl}>Students</a>
            </li>
            <li class="nav-item ${activeNavItem.equals("teachers") ? "active" : ""}">
                <a class="nav-link" href=${teachersUrl}>Teachers</a>
            </li>
        </ul>
    </div>
    <a href="/elective/logout" class="btn btn-primary">Log out</a>
</nav>