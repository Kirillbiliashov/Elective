<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<c:if test="${next le pagesCount}">
    <li class="page-item"><a class="page-link" href="?page=${next}&display=${param.display ne null ? param.display : 1}">${next}</a></li>
</c:if>
<li class="page-item ${next > pagesCount ? "disabled" : ""}"><a class="page-link" href="?page=${next}&display=${param.display ne null ? param.display : 1}"><fmt:message key="next"/></a></li>