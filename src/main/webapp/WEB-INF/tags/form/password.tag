<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="password"><fmt:message key="user.password" /></label>
    <input type="password" class="form-control" id="password" required  minlength="8" maxlength="64" placeholder=<fmt:message key="user.password" /> name="password">
</div>