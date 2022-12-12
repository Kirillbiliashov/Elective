<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="text" %>
<div class="form-btn-container">
    <button type="submit" class="btn btn-primary">${text}</button>
</div>
