package com.exadel.friendface.logon;

import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.exadel.friendface.servletutil.ServletUtil.forwardPage;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		Validator validator = new Validator();
    	String loginEmail = request.getParameter("loginEmail");
    	String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String username = request.getParameter("username");
		String usersurname = request.getParameter("usersurname");
		
		try {
			validator.validateEmail(loginEmail);
			validator.validateTextString(username);
			validator.validateTextString(usersurname);
			validator.validatePassword(password);
			validator.validatePassword(passwordConfirmation);
			validator.validatePassword(password, passwordConfirmation);

			// registration here
			response.sendRedirect("/friendface/underconstruction");
		}
		catch (ValidationException ve) {
			forwardPage(this, request, response, ve.toString(), "/registration");
		}
	}
}
