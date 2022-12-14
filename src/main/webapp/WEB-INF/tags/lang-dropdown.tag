<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="dropdown language-dropdown">
    <button class="btn btn-secondary dropdown-toggle btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <fmt:message key="language"/>
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="?lang=en"><fmt:message key="english"/></a>
        <a class="dropdown-item" href="?lang=ru"><fmt:message key="russian"/></a>
    </div>
</div>