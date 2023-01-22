<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="isCourse" required="false" %>
<c:set var="display" value="${param.display ne null ? param.display : (isCourse ? 1 : 5)}"/>
<c:if test="${next le pagesCount}">
    <li class="page-item">
        <a class="page-link" href="?page=${next}&display=${display}">${next}</a>
    </li></c:if>
<li class="page-item ${next > pagesCount ? "disabled" : ""}">
    <a class="page-link" href="?page=${next}&display=${display}"><fmt:message key="next"/></a>
</li>