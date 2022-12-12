<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="startDate"><fmt:message key="course.start_date" /></label>
    <input type="date" class="form-control" id="startDate" min="${minDate}" placeholder=<fmt:message key="course.start_date" /> name="startDate">
</div>