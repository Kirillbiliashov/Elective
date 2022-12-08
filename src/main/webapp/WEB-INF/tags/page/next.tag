<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${next <= pagesCount}">
    <li class="page-item"><a class="page-link" href="?page=${next}">${next}</a></li>
</c:if>
<li class="page-item ${next > pagesCount ? "disabled" : ""}"><a class="page-link" href="?page=${next}">Next</a></li>