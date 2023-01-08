'use strict'

const validateForm = () => {
    const form = document.forms[0];
    const logins = '${logins}';
    if (logins.includes(form["username"].value)) {
        alert('<fmt:message key="username_exists"/>');
        return false;
    }
    if (logins.includes(form["email"].value)) {
        alert('<fmt:message key="email_exists"/>');
        return false;
    }
    const password = form["password"].value;
    const confirmPassword = form["confirmPassword"].value;
    if (password !== confirmPassword) {
        alert('<fmt:message key="no_password_match"/>');
        return false;
    }
    return true;
};

