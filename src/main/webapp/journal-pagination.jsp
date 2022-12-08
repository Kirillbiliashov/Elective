<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<html>
<body>
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <page:prev/>
    <page:current/>
    <page:next/>
  </ul>
</nav>
</body>
</html>
