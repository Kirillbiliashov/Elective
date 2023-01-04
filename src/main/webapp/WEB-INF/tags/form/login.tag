<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="login"><fmt:message key="user.login" /></label>
    <input type="text" class="form-control" id="login" required minlength="8" maxlength="50" placeholder=<fmt:message key="user.login"/> name="login">
</div>