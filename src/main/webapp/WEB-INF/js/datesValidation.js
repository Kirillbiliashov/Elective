'use strict'

const validateForm = () => {
    const form = document.forms[0];
    const startDate = form["startDate"].value;
    const endDate = form["endDate"].value;
    const datesValid = endDate > startDate;
    if (!datesValid) {
        alert('<fmt:message key="date_error"/>');
        return false;
    }
    const teacherSelect = form["teacherSelect"].value;
    const topicSelect = form["topicSelect"].value;
    if (topicSelect === "0") {
        alert('<fmt:message key="topic_error"/>');
        return false;
    }
    if (teacherSelect === "0") {
        alert('<fmt:message key="teacher_error"/>');
        return false;
    }
    return true;
};

