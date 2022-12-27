<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="minDate" value="${now}" pattern="yyyy-MM-dd" />
<%@ attribute name="value" required="false" %>
<div class="form-group">
    <label for="startDate"><fmt:message key="course.start_date" /></label>
    <input type="date" class="form-control" id="startDate" min="${minDate}" value="${value}" placeholder=<fmt:message key="course.start_date" /> name="startDate">
</div>