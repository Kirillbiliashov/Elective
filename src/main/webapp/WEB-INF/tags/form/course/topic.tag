<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<div class="form-group">
    <label for="topicSelect"><fmt:message key="topic" /></label>
    <select class="form-control" id="topicSelect" name="topicId">
        <c:forEach items="${topics}" var="topic">
            <option value="${topic.id}">${topic.name}</option>
        </c:forEach>
    </select>
</div>