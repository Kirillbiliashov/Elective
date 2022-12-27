<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="value" required="false" %>
<div class="form-group">
    <label for="topicSelect"><fmt:message key="topic" /></label>
    <select class="form-control" id="topicSelect" name="topicId">
        <c:if test="${value eq null}">
            <option selected value="0"><fmt:message key="select_topic"/></option>
        </c:if>
        <c:forEach items="${topics}" var="topic">
        <option value="${topic.id}" <c:if test="${value == topic.id}">selected</c:if>>${topic.name}</option>
        </c:forEach>
    </select>
</div>