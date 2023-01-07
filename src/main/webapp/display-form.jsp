<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="display-container">
  <form method="get">
    <div class="form-group">
      <label for="display"><fmt:message key="records_per_page"/></label>
      <input class="form-control display-field" type="number" id ="display" name="display" min="1" value="${param.display}"/>
    </div>
    <div class="form-group">
      <input type="submit" value="<fmt:message key="display"/>" class="btn btn-primary">
    </div>
  </form>
</div>