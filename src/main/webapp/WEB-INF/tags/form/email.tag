<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="email"><fmt:message key="user.email" /></label>
    <input type="email" class="form-control" id="email" required minlength="8" maxlength="50" placeholder=<fmt:message key="user.email"/> name="email">
</div>