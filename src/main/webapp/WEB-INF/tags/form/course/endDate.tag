<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="endDate"><fmt:message key="course.end_date" /></label>
    <input type="date" class="form-control" id="endDate" min="${minDate}" placeholder=<fmt:message key="course.end_date" /> name="endDate">
</div>