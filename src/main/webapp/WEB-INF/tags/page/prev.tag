<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class="page-item ${prev == 0 ? "disabled" : ""}"><a class="page-link" href="?page=${prev}">Previous</a></li>
<c:if test="${prev != 0}">
    <li class="page-item"><a class="page-link" href="?page=${prev}">${prev}</a></li>
</c:if>