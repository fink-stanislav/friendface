<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        response.sendRedirect("/friendface/registration");
        return;
    } else {
        requestMessage = (String) session.getAttribute("message");
        requestMessage = avoidNullValue(requestMessage);
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css">
    <link rel="stylesheet" type="text/css" href="css/forms.css">
    <link rel="stylesheet" type="text/css" href="css/inputs.css">
    <link type="text/javascript" href="js/validation.js">
    <title>Регистрация</title>
</head>
<body>
<div id="container">

    <f:requestEncoding value="UTF-8"/>
    <jsp:include page="/header.jsp">
        <jsp:param name="headerType" value="oneRef"/>
        <jsp:param name="rightReference" value="logon"/>
        <jsp:param name="rightReferenceName" value="вход"/>
        <jsp:param name="title" value="регистрация"/>
    </jsp:include>

    <div id="content">
        <div id="reg-form">
            <form name="userdataform" method="POST" action="/friendface/RegistrationServlet">

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
                    <input type="password" name="passwordConfirmation">
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

<jsp:include page="/footer.jsp"/>

</body>
</html>