<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<div class="form-group">
    <label for="teacherSelect"><fmt:message key="teacher" /></label>
    <select class="form-control" id="teacherSelect" name="teacherId">
        <option value="0" selected>None</option>
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.id}"><teacher:fullName target="${teacher}"/></option>
        </c:forEach>
    </select>
</div>