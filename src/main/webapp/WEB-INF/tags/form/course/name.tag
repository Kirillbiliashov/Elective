<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="name"><fmt:message key="course.name" /></label>
    <input type="text" class="form-control" id="name" name="name" maxlength="50" required placeholder=<fmt:message key="course.name" />>
</div>