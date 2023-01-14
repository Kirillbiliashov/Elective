<%@ taglib prefix="teacher" uri="/WEB-INF/tld/account.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="selection-form-container">
    <form>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="sort"><fmt:message key="sort.sort_by"/></label>
                <select class="form-control" name="sort" id="sort">
                    <c:forEach items="${sortTypes}" var="type">
                        <option
                                <c:if test="${param.sort eq type}">selected</c:if> value="${type}">
                            <fmt:message key="sort.${type}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="teacher"><fmt:message key="teacher"/></label>
                <select class="form-control" name="teacher" id="teacher">
                    <option selected value="Any"><fmt:message key="teacher.any"/></option>
                    <c:forEach items="${teachers}" var="teacher">
                        <option <c:if test="${param.teacher eq teacher.fullName}">selected</c:if> value="${teacher.fullName}">${teacher.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="topic"><fmt:message key="topic"/></label>
                <select class="form-control" name="topic" id="topic">
                    <option selected value="Any"><fmt:message key="topic.any"/></option>
                    <c:forEach items="${topics}" var="topic">
                        <option <c:if test="${param.topic eq topic.name}">selected</c:if> value="${topic.name}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="selection.apply"/></button>
    </form>
</div>