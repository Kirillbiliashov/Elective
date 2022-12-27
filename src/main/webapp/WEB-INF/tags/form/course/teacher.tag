<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="value" required="false" %>
<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<div class="form-group">
    <label for="teacherSelect"><fmt:message key="teacher" /></label>
    <select class="form-control" id="teacherSelect" name="teacherId">
        <c:if test="${value eq null}">
            <option value="0" selected><fmt:message key="select_teacher"/></option>
        </c:if>
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.id}" <c:if test="${value == teacher.id}">selected</c:if>><teacher:fullName target="${teacher}"/></option>
        </c:forEach>
    </select>
</div>