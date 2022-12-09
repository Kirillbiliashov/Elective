<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<li class="page-item ${prev == 0 ? "disabled" : ""}"><a class="page-link" href="?page=${prev}"><fmt:message key="previous"/></a></li>
<c:if test="${prev != 0}">
    <li class="page-item"><a class="page-link" href="?page=${prev}&lang=${param.lang}">${prev}</a></li>
</c:if>