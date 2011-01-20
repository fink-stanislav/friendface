<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css">
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

</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>
