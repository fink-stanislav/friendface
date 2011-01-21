<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="registrationBean" class="com.exadel.friendface.pagebeans.RegistrationBean" scope="session"/>

<jsp:setProperty name="registrationBean" property="*"/>
<c:if test='${not empty requestScope.message}'>
    <jsp:setProperty name="registrationBean" property="errormessage" value='${requestScope.message}'/>
    <c:redirect url="registration"/>
</c:if>
<c:otherwise>
<jsp:setProperty name="registrationBean" property="errormessage" value=""/>
    </c:otherwise>

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

                <div>
                    <span class="required-field">Логин (e-mail)</span>
                </div>
                <div>
                    <input type="text" name="loginEmail" value=${registrationBean.loginEmail}>
                </div>

                <div>
                    <span>Имя</span>
                </div>
                <div>
                    <input type="text" name="username" value=${registrationBean.username}>
                </div>

                <div>
                    <span>Фамилия</span>
                </div>
                <div>
                    <input type="text" name="usersurname" value=${registrationBean.usersurname}>
                </div>

                <div>
                    <span class="required-field">Пароль</span>
                </div>

                <div>
                    <input type="password" name="password">
                </div>

                <div>
                    <span class="required-field">Проверка пароля</span>
                </div>

                <div>
                    <input type="password" name="passwordConfirmation">
                </div>

                <c:choose>
                    <c:when test='${not empty registrationBean.errormessage}'>
                        ${registrationBean.errormessage}
                    </c:when>
                    <c:otherwise>
                        <jsp:text>&nbsp;</jsp:text>
                    </c:otherwise>
                </c:choose>

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