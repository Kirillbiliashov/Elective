<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text"/>
<div class="display-container">
  <form method="get">
    <input type="hidden" name="page" value="1"/>
    <div class="form-group">
      <label for="display"><fmt:message key="records_per_page"/></label>
      <input class="form-control display-field" type="number" id ="display" name="display" min="1"
             value="${not empty param.display ? param.display : 5}"/>
    </div>
    <div class="form-group">
      <input type="submit" value="<fmt:message key="display"/>" class="btn btn-primary">
    </div>
  </form>
</div>