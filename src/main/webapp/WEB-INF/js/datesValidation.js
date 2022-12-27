'use strict'

const validateForm = () => {
    const form = document.forms[0];
    const startDate = form["startDate"].value;
    const endDate = form["endDate"].value;
    const datesValid = endDate > startDate;
    if (!datesValid) {
        alert("end date cannot be before start date");
        return false;
    }
    const teacherSelect = form["teacherSelect"].value;
    const topicSelect = form["topicSelect"].value;
    if (topicSelect === "0") {
        alert("Please select topic");
        return false;
    }
    if (teacherSelect === "0") {
        alert("Please select teacher");
        return false;
    }
    return true;
};

