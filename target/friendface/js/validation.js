function validateLogin(login) {
    var log = login.toLowerCase();
	var domain1 = "[a-z][a-z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
    var domain2 = "([a-z]){2,4}";
	return log.match(domain1 + "@" + domain1 + "\\." + domain2);
}

function validatePassword(password) {
	return password.match("[a-z][a-z[0-9]\u005F\u002D]*[a-z||0-9]");
}

function validateTextString(textString) {
	return textString.match("\w\s");
}

function numberString(numberString) {
	return numberString.match("\d");
}

function logonFormValidation(login, password) {
	var r1 = false, r2 = false;
	if (validateLogin(login)) {
		r1 = true;
	}
	if (validatePassword(password)) {
		r2 = true;
	}
	return r1 & r2;
}

function pwdEquality(pwd1, pwd2) {}

function registrationFormValidation(login, name, surname, password1, password2) {
	var logonNpass = false;
	logonNpass = logonFormValidation(login, password) & (password1 == password2);
	if (logonNpass && validateString(name) && validateString(surname)) {
		return true;
	} else {
		return false;
	}
}