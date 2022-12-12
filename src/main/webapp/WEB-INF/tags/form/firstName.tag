<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="fname"><fmt:message key="first_name"/></label>
    <input type="text" name="firstName" id="fname" class="form-control" minlength="2" maxlength="20" required placeholder=<fmt:message key="first_name"/>>
</div>