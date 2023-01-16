<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="value" required="false" %>
<div class="form-group">
    <label for="description"><fmt:message key="course.description"/></label>
    <textarea class="form-control" id="description" name="description" rows="3" placeholder="<fmt:message key="course.description"/>">${value}</textarea>
</div>