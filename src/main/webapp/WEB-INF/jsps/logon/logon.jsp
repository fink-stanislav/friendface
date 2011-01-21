<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="logonBean" class="com.exadel.friendface.pagebeans.LogonBean" scope="session"/>

<jsp:setProperty name="logonBean" property="*"/>
<c:if test='${not empty requestScope.message}'>
    <jsp:setProperty name="logonBean" property="errormessage" value='${requestScope.message}'/>
    <c:redirect url="logon"/>
</c:if>

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

                <div>
                    <span>Имя пользователя</span>
                </div>
                <div>
                    <input type="text" name="loginEmail" value='${logonBean.loginEmail}'>
                </div>

                <div class="formLine">
                    <span>Пароль</span>
                </div>
                <div>
                    <input type="password" name="password">
                </div>

                <c:choose>
                    <c:when test='${not empty logonBean.errormessage}'>
                        ${logonBean.errormessage}
                    </c:when>
                    <c:otherwise>
                        <jsp:text>&nbsp;</jsp:text>
                    </c:otherwise>
                </c:choose>

                <hr/>

                <div class="formLine">
                    <input type="submit" name="submitbutton" value="Войти">
                    <input type="reset" name="resetbutton" value="Отмена">
                </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>