<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="fname"><fmt:message key="user.first_name"/></label>
    <input type="text" name="firstName" id="fname" class="form-control" placeholder="<fmt:message key="user.first_name"/>" minlength="2" maxlength="20" required>
</div>