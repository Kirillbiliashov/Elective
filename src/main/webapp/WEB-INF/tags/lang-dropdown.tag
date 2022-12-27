<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="dropdown language-dropdown">
    <button class="btn btn-secondary dropdown-toggle btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <fmt:message key="language"/>
    </button>
    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item"
           <c:if test="${param.page ne null}">href="?page=${param.page}&lang=en"</c:if>
           <c:if test="${param.page eq null}">href="?lang=en"</c:if>>
        <fmt:message key="english"/></a>
        <a class="dropdown-item"
           <c:if test="${param.page ne null}">href="?page=${param.page}&lang=ru"</c:if>
           <c:if test="${param.page eq null}">href="?lang=ru"</c:if>><fmt:message key="russian"/></a>
    </div>
</div>
