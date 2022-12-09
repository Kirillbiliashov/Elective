<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="password"><fmt:message key="password" /></label>
    <input type="password" class="form-control" id="password" placeholder=<fmt:message key="password" /> name="password">
</div>