'use strict'

const validateForm = () => {
    const form = document.forms[0];
    const password = form["password"].value;
    const confirmPassword = form["confirmPassword"].value;
    if (password !== confirmPassword) {
        alert("Confirm password correctly");
        return false;
    }
    return true;
};

