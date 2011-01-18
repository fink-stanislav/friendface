package com.exadel.friendface.validation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class Validator {
		
	public Validator() {}

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
        Pattern p = Pattern.compile("([a-z]){1,20}");
		Matcher m = p.matcher(textString.toLowerCase());
		return m.matches();
    }

    public boolean isPasswordLongEnough(String password) {
        return password.length() > 5;
    }
		
	public void validateEmail(String email) 
	    throws ValidationException {
		
		if (isBlank(email)) {
			throw new ValidationException("No email.");
		}

        if (!isEmailMatch(email)) {
        	throw new ValidationException("Specified email does not match.");
        }
	}
	
	public void validatePassword(String password) 
	    throws ValidationException {

		if (isBlank(password)) {
			throw new ValidationException("No password.");
		} 

        if (!isPasswordLongEnough(password)) {
            throw new ValidationException("Specified password is too short");
        }

        if (!isPasswordMatch(password)) {
            throw new ValidationException("Specified password does not match.");
        }
	}

    public void validatePassword(String password, String passwordConfirmation)
	    throws ValidationException {

		if (isBlank(password)) {
			throw new ValidationException("No password.");
		}

        if (isBlank(passwordConfirmation)) {
			throw new ValidationException("No confirmation password.");
		}

        if (!password.equals(passwordConfirmation)) {
			throw new ValidationException("First password is not equal to second.");
		}
	}

	public void validateTextString(String textString)
	    throws ValidationException {

		if (isBlank(textString)) {
			throw new ValidationException("No text.");
		}

		if (!isTextStringMatch(textString)) {
			throw new ValidationException("Specified string does not match.");
		}
	}
}
