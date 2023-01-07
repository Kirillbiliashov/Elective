<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<button type="button" class="btn btn-link description-btn" data-toggle="modal" data-target="#descModal/${course.id}">
    <fmt:message key="course.description"/>
</button>
<div class="modal fade" id="descModal/${course.id}" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">${course.name}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ${course.description}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>