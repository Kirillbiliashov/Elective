'use strict'

const validateForm = () => {
    const form = document.forms[0];
    const startDate = form["startDate"].value;
    const endDate = form["endDate"].value;
    const datesValid = endDate > startDate;
    console.log("i am here");
    if (datesValid) return true;
    alert("end date cannot be before start date");
    return false;
};