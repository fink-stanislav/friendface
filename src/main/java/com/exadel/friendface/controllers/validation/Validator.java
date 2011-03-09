package com.exadel.friendface.controllers.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.isBlank;

public class Validator {
    private Integer occurrences = 0;
    private Integer failNumber = 0;

    public Validator() {}

    public Validator(Integer occurrences) {
        this.occurrences = occurrences;
    }

    public boolean isEmailMatch(String email) {
        String name = "[a-z][a-z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
        String domain = "([a-z]){2,4}";
        Pattern p = Pattern.compile(name + "@" + name + "\\." + domain);
        Matcher m = p.matcher(email.toLowerCase());
        return m.matches();
    }

    public boolean isPasswordMatch(String password) {
        Pattern p = Pattern.compile("[a-z[0-9]\u005F\u002D]*[a-z||0-9]");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public boolean isTextStringMatch(String textString) {
        Pattern p = Pattern.compile("([a-zа-я]){1,20}");
        Matcher m = p.matcher(textString.toLowerCase());
        return m.matches();
    }

    public boolean isPasswordLongEnough(String password) {
        return password.length() > 5;
    }

    public void validateEmail(String email) throws ValidationException {
        if (isBlank(email)) {
            throw new ValidationException("no.email");
        }

        if (!isEmailMatch(email)) {
            throw new ValidationException("email.doesnt.match");
        }
    }

    public void validatePassword(String password) throws ValidationException {
        if (isBlank(password)) {
            throw new ValidationException("no.password");
        }

        if (!isPasswordLongEnough(password)) {
            throw new ValidationException("password.too.short");
        }

        if (!isPasswordMatch(password)) {
            throw new ValidationException("password.doesnt.match");
        }
    }

    public void validatePassword(String password, String passwordConfirmation) throws ValidationException {
        if (isBlank(password)) {
            throw new ValidationException("no.password");
        }

        if (isBlank(passwordConfirmation)) {
            throw new ValidationException("no.confirmation.password");
        }

        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("password.not.equals.to.conf");
        }
    }

    public void validateName(String textString) throws ValidationException {
        if (isBlank(textString)) {
            throw new ValidationException("no.name");
        }

        if (!isTextStringMatch(textString)) {
            throw new ValidationException("name.doesnt.match");
        }
    }

    public void notBlank(String textString) throws ValidationException {
        if (isBlank(textString)) {
            throw new ValidationException("no.text.entered");
        }
    }

    public void increasingNotBlank(String textString) throws ValidationException {
        if (isBlank(textString)) {
            failNumber++;
            if (occurrences.equals(failNumber)) {
                throw new ValidationException("no.text.entered");
            }
        }
    }
}
