<%--
  Created by IntelliJ IDEA.
  User: sfink
  Date: 1/20/11
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css"/>
    <title>Under construction</title>
</head>
<body>
<div id="container">


    <f:requestEncoding value="UTF-8"/>
    <jsp:include page="/header.jsp">
        <jsp:param name="title" value="under construction"/>
    </jsp:include>

    <div id="content">
        Страница временно недоступна из-за технических работ на сайте.
    </div>
</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>