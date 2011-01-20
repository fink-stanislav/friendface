<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String requestMessage = (String) request.getAttribute("message");
    if (requestMessage != null) {
        session.setAttribute("message", requestMessage);
        response.sendRedirect("/friendface/wrongdata");
        return;
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css"/>
    <title>Ошибка</title>
</head>
<body>
<div id="container">
    <div id="header">
        <div id="tagline">
            <div id="headertxt"><a href="../index.html" title="главная страница°">friendface</a> - ошибка ввода</div>
        </div>
    </div>
    <div id="content">
        <%=(String) session.getAttribute("message") %>
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