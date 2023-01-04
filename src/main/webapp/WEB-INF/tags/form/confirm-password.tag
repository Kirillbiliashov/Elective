<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="confirmPassword"><fmt:message key="user.confirm_password" /></label>
    <input type="password" class="form-control" id="confirmPassword" required  minlength="8" maxlength="64" placeholder="<fmt:message key="user.confirm_password" />" name="confirmPassword">
</div>