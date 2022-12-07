<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
      <li class="page-item ${prev == 0 ? "disabled" : ""}"><a class="page-link" href="?page=${prev}">Previous</a></li>
      <c:if test="${prev != 0}">
        <li class="page-item"><a class="page-link" href="?page=${prev}">${prev}</a></li>
      </c:if>
      <li class="page-item active"><a class="page-link" href="?page=${page}">${page}</a></li>
      <c:if test="${next <= pagesCount}">
        <li class="page-item"><a class="page-link" href="?page=${next}">${next}</a></li>
      </c:if>
      <li class="page-item ${next > pagesCount ? "disabled" : ""}"><a class="page-link" href="?page=${next}">Next</a></li>
    </ul>
  </nav>
</body>
</html>
