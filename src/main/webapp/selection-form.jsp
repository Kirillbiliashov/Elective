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
                    <option selected value="none"><fmt:message key="sort.none"/></option>
                    <option value="name"><fmt:message key="sort.name_asc"/></option>
                    <option value="name_desc"><fmt:message key="sort.name_desc"/></option>
                    <option value="duration"><fmt:message key="sort.duration_asc"/></option>
                    <option value="duration_desc"><fmt:message key="sort.duration_desc"/></option>
                    <option value="students"><fmt:message key="sort.students_asc"/></option>
                    <option value="students_desc"><fmt:message key="sort.students_desc"/></option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="teacher"><fmt:message key="teacher"/></label>
                <select class="form-control" name="teacher" id="teacher">
                    <option selected value="Any"><fmt:message key="teacher.any"/></option>
                    <c:forEach items="${teachers}" var="teacher">
                        <option value="${teacher.fullName}">${teacher.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="topic"><fmt:message key="topic"/></label>
                <select class="form-control" name="topic" id="topic">
                    <option selected value="Any"><fmt:message key="topic.any"/></option>
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic.name}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="selection.apply"/></button>
    </form>
</div>