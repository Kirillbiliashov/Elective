<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<c:if test="${next <= pagesCount}">
    <li class="page-item"><a class="page-link" href="?page=${next}">${next}</a></li>
</c:if>
<li class="page-item ${next > pagesCount ? "disabled" : ""}"><a class="page-link" href="?page=${next}&lang=${param.lang}"><fmt:message key="next"/></a></li>