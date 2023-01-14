<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="isCourse" required="false" %>
<li class="page-item ${prev eq 0 ? "disabled" : ""}">
    <a class="page-link" href="?page=${prev}&display=${param.display ne null ? param.display : (isCourse ? 1 : 5)}"><fmt:message key="previous"/></a></li>
<c:if test="${prev ne 0}">
    <li class="page-item"><a class="page-link" href="?page=${prev}&display=${param.display ne null ? param.display : (isCourse ? 1 : 5)}">${prev}</a></li>
</c:if>