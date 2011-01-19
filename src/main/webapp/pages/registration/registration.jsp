<%!
    String loginEmail;
    String username;
    String usersurname;

    public String avoidNullValue(String textString) {
        if (textString == null) {
            return "";
        }
        return textString;
    }
%>

<%
    String requestMessage = (String) request.getAttribute("message");
    if (requestMessage != null) {
        session.setAttribute("message", requestMessage);

        loginEmail = (String) request.getParameter("loginEmail");
        username = (String) request.getParameter("username");
        usersurname = (String) request.getParameter("usersurname");

        response.sendRedirect(request.getContextPath() + "/pages/registration/registration.jsp");
        return;
    }
    else {
        requestMessage = (String) session.getAttribute("message");
        requestMessage = avoidNullValue(requestMessage);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="../../css/commonstyle.css">
 <link rel="stylesheet" type="text/css" href="../../css/forms.css">
 <link rel="stylesheet" type="text/css" href="../../css/inputs.css">
 <link type="text/javascript" href="../../js/validation.js">
 <title>Регистрация</title>
</head>
<body>
 <div id="container">
  <div id="header">
   <div id="headertxt"><a href="../../index.html" title="главная страница">friendface</a> - регистрация на сайте</div>
   <div id="headrefs">
    <a href="../logon/logon.html" title="вход">вход</a>
   </div>
  </div>
 
  <div id="content">
  <div id="reg-form">
   <form name="userdataform" method="POST" action="../../RegistrationServlet">
	
	<div class="formLine">
	 <span class="required-field">Логин (e-mail)</span>
	</div>
	<div>
	 <input type="text" name="loginEmail" value=<%=avoidNullValue(loginEmail)%>>
	</div>
	 
	<div class="formLine">
	 <span>Имя</span>
	</div>
	<div>
	 <input type="text" name="username" value=<%=avoidNullValue(username)%>>
	</div>
	 
	<div class="formLine">
	 <span>Фамилия</span>
	</div>
	<div>
	 <input type="text" name="usersurname" value=<%=avoidNullValue(usersurname)%>>
	</div>
	 
	<div class="formLine">
	 <span class="required-field">Пароль</span>
	</div>

	<div> 
	 <input type="password" name="password">
	</div>
	 
	<div class="formLine">
	 <span class="required-field">Проверка пароля</span>
	</div>

	<div> 
	 <input type="password" name="passwordConfirmation" >
	</div>

       <%=requestMessage %>

    <hr/>
	  
	<div class="formLine">
	 <input type="submit" name="submitbutton" value="Зарегистрироваться">
	 <input type="reset" name="resetbutton" value="Отмена">
	</div>
	 
   </form>
  </div>
  </div>
 </div>
   
 <div id="footer">
  <div id="footrefs">
   <a href="" title="english">eng</a>
    &nbsp;|&nbsp;
   <a href="" title="русский">rus</a>
  </div>
  <div id="footertxt">
   cybersyphon, 2010
  </div>
 </div>
   
</body>
</html>