<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css">
    <link rel="stylesheet" type="text/css" href="css/forms.css">
    <link type="text/javascript" href="/js/validation.js">
    <title>Вход</title>
</head>
<body>
<div id="container">

    <f:requestEncoding value="UTF-8"/>
    <jsp:include page="/header.jsp">
        <jsp:param name="headerType" value="oneRef"/>
        <jsp:param name="rightReference" value="registration"/>
        <jsp:param name="rightReferenceName" value="регистрация"/>
        <jsp:param name="title" value="регистрация"/>
    </jsp:include>

    <div id="content">
        <div id="login-form">
            <form name="userdataform" method="POST" action="/friendface/LogonServlet">

            <div class="formLine">
                <label for="loginEmail">Имя пользователя</label>
            </div>
            <div>
                <input type="text" name="loginEmail"/>
            </div>

            <div class="formLine">
                <label for="password">Пароль</label>
            </div>
            <div>
                <input type="password" name="password"/>
            </div>

            <hr/>

            <div class="formLine">
                <input type="submit" name="submitbutton" value="Войти"/>
                <input type="reset" name="resetbutton" value="Отмена"/>
            </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>