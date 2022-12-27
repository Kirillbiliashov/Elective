<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="username"><fmt:message key="user.username" /></label>
    <input type="text" class="form-control" id="username" required minlength="8" maxlength="50" placeholder=<fmt:message key="user.username"/> name="username">
</div>