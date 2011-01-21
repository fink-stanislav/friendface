<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/commonstyle.css">
        <link rel="stylesheet" type="text/css" href="css/doublecontainer.css">
        <title>Welcome!</title>
    </head>
    <body>

    <div id="container">

        <f:requestEncoding value="UTF-8"/>
        <jsp:include page="/header.jsp">
            <jsp:param name="headerType" value="twoRefs"/>
            <jsp:param name="leftReference" value="registration"/>
            <jsp:param name="leftReferenceName" value="регистрация"/>
            <jsp:param name="rightReference" value="logon"/>
            <jsp:param name="rightReferenceName" value="вход"/>
        </jsp:include>

        <div id="content">
            <div id="article-header-side">
                Welcome!
            </div>
            <div id="article-side">
                Hi, username! Here is a place, where you can meet friends and get their pages flooded beyond all
                    recognition! Time to try!

            </div>
        </div>

    </div>

    <jsp:include page="/footer.jsp"/>

    </body>
    </html>
