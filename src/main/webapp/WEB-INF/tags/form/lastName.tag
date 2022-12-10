<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="lname"><fmt:message key="last_name"/></label>
    <input type="text" name="lastName" id="lname" class="form-control" placeholder=<fmt:message key="last_name"/>>
</div>