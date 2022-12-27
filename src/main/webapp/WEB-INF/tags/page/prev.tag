<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<li class="page-item ${prev eq 0 ? "disabled" : ""}"><a class="page-link"
                                                        href="?page=${prev}&lang=${param.lang}"><fmt:message
        key="previous"/></a></li>
<c:if test="${prev ne 0}">
    <li class="page-item"><a class="page-link" href="?page=${prev}&lang=${param.lang}">${prev}</a></li>
</c:if>