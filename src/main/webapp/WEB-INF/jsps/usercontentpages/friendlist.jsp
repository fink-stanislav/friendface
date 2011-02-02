<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../../css/commonstyle.css">
    <link rel="stylesheet" type="text/css" href="../../../css/forms.css">
    <link rel="stylesheet" type="text/css" href="../../../css/menu.css">
    <title>Друзья</title>
</head>
<body>
<div id="container">

    <div id="header">
        <div id="headertxt"><a href="../../WEB-INF/welcomepage.jsp" title="главная страница">friendface</a> - друзья</div>
        <div id="headrefs">
            <a href="../../../pages/friends" title="настройки">настройки</a>
            <a href="../../../pages/friends" title="выход">выход</a>
        </div>
    </div>

    <div id="content">
        <div id="menu">
            <div class="menuitem"><a href="../../../pages/friends" title="моя страница">
                <div class="ref">моя страница</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/friends" title="друзья">
                <div class="ref">друзья</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/friends" title="сообщения">
                <div class="ref">сообщения</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/friends" title="фото">
                <div class="ref">фото</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/friends" title="видео">
                <div class="ref">видео</div>
            </a></div>
        </div>
        <div id="vertical-line"></div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>