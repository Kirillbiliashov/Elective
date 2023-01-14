<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<%@ attribute name="isCourse" required="false" %>
<li class="page-item active"><a class="page-link" href="?page=${page}&display=${param.display ne null ? param.display : (isCourse ? 1 : 5)}">${page}</a></li>