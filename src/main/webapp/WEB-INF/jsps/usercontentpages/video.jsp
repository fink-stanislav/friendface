<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="../../../css/commonstyle.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/forms.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/menu.css"/>
    <title>Видео</title>
</head>
<body>
<div id="container">

    <div id="header">
        <div id="headertxt"><a href="../../WEB-INF/welcomepage.jsp" title="главная страница">friendface</a> - видео</div>
        <div id="headrefs">
            <a href="../../../pages/video" title="настройки">настройки</a>
            <a href="../../../pages/video" title="выход">выход</a>
        </div>
    </div>

    <div id="content">
        <div id="menu">
            <div class="menuitem"><a href="../../../pages/video" title="моя страница">
                <div class="ref">моя страница</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/video" title="друзья">
                <div class="ref">друзья</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/video" title="сообщения">
                <div class="ref">сообщения</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/video" title="фото">
                <div class="ref">фото</div>
            </a></div>
            <div class="menuitem"><a href="../../../pages/video" title="видео">
                <div class="ref">видео</div>
            </a></div>
        </div>
        <div id="vertical-line"></div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>

</body>
</html>