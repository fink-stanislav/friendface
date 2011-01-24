<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/commonstyle.css"/>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="css/userdata.css"/>
    <link rel="stylesheet" type="text/css" href="css/wall.css"/>
    <link type="text/javascript" href="js/validation.js"/>
    <title>Username</title>
</head>
<body>
<div id="container">
    <f:requestEncoding value="UTF-8"/>
    <jsp:include page="/header.jsp">
        <jsp:param name="headerType" value="twoRef"/>
        <jsp:param name="leftReference" value=""/>
        <jsp:param name="leftReferenceName" value="настройки"/>
        <jsp:param name="rightReference" value=""/>
        <jsp:param name="rightReferenceName" value="выход"/>
    </jsp:include>

    <div id="menu-and-userdata">
        <div id="content">
            <div id="menu">
                <div class="menuitem"><a href="" title="моя страница">
                    <div class="ref">моя страница</div>
                </a></div>
                <div class="menuitem"><a href="" title="друзья">
                    <div class="ref">друзья</div>
                </a></div>
                <div class="menuitem"><a href="" title="сообщения">
                    <div class="ref">сообщения</div>
                </a></div>
                <div class="menuitem"><a href="" title="фото">
                    <div class="ref">фото</div>
                </a></div>
                <div class="menuitem"><a href="" title="видео">
                    <div class="ref">видео</div>
                </a></div>
            </div>
            <div id="vertical-line"></div>

            <div id="userdata">
                <div id="userpic">
                    <img class="image" src="../../images/image.png"/>
                </div>
                <div id="common-info">
                    <div class="horizontal-block-center">
                        <div class="common-info-line">Финк</div>
                        <div class="common-info-line">Станислав</div>
                    </div>
                    <div class="horizontal-block-normal">
                        <div class="common-info-line">Имя параметра:</div>
                        <div class="common-info-line">Значение параметра</div>
                    </div>
                    <div class="horizontal-block-normal">
                        <div class="common-info-line">Номер аськи:</div>
                        <div class="common-info-line">395-446-281</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="wall">
        <div class="wall-message">
            <div class="wall-message-head">
                <a href="" title="имя отправителя">имя отправителя</a>
                &nbsp|&nbsp
                <a href="" title="удалить">удалить</a>
            </div>
            <div class="wall-message-text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eu dui massa, a mollis nunc. Nam
                ornare lectus sit amet lorem dictum accumsan. Donec at nibh quis augue facilisis pharetra. Pellentesque
                habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris cursus tortor
                non erat vehicula ultricies. Etiam pretium orci ut neque luctus ac gravida neque laoreet. Nunc id metus
                leo, ut facilisis nulla. Proin facilisis, dolor in condimentum mollis, justo eros interdum justo, ut
                pretium odio urna nec mauris. Cras semper nisl at ipsum laoreet ornare. Donec vitae neque neque, nec
                dignissim odio. Sed sollicitudin sodales metus, in imperdiet felis vehicula ac. Maecenas libero magna,
                tincidunt at bibendum venenatis, aliquam eu leo. Ut id sem quam, eget dapibus nibh. Quisque iaculis odio
                eu nunc posuere tempor. Nullam dictum posuere nunc, ut pulvinar dolor pellentesque nec. Aliquam
                condimentum felis ut mi tempus sodales. Ut faucibus mi sit amet velit varius laoreet id at ligula. Nunc
                metus orci, dignissim a bibendum pellentesque, placerat a enim.
            </div>
        </div>

        <div class="wall-message">
            <div class="wall-message-head">
                <a href="" title="имя отправителя">имя отправителя</a>
                &nbsp|&nbsp
                <a href="" title="удалить">удалить</a>
            </div>
            <div class="wall-message-text">
                In venenatis, enim ut interdum pulvinar, est ipsum eleifend libero, at congue odio eros eget dui.
                Praesent eleifend euismod turpis in convallis. Integer eleifend vestibulum metus, a malesuada augue
                rhoncus at. Mauris dapibus purus a augue lacinia at ultrices lacus molestie. Pellentesque quis est sit
                amet ligula tempor fringilla. Ut id mauris vitae elit malesuada fermentum. Donec tempor turpis non purus
                ullamcorper quis semper turpis malesuada. Sed gravida nisl a justo posuere sed viverra augue commodo.
                Donec pellentesque orci eu nisl vehicula lacinia. Curabitur volutpat lacinia gravida. Curabitur mollis
                dapibus odio, quis vehicula nunc laoreet in. Donec hendrerit lorem sed lacus sodales faucibus. Sed
                tincidunt ornare mattis. Nam at neque vitae est congue sagittis at hendrerit diam. Aliquam metus nibh,
                vestibulum quis commodo ut, varius non orci. Pellentesque habitant morbi tristique senectus et netus et
                malesuada fames ac turpis egestas. Nam vitae venenatis lectus. Donec porta, erat eu porttitor hendrerit,
                velit turpis rhoncus nulla, et dignissim sapien nibh sagittis libero.
            </div>
        </div>

        <div class="wall-message">
            <div class="wall-message-head">
                <a href="" title="имя отправителя">имя отправителя</a>
                &nbsp|&nbsp
                <a href="" title="удалить">удалить</a>
            </div>
            <div class="wall-message-text">
                Quisque quis leo quam. Etiam varius fermentum risus, in iaculis odio blandit ut. Donec aliquet metus sed
                enim aliquet id mattis odio dignissim. Quisque nec ante lacus. Praesent lorem augue, faucibus eu
                malesuada nec, blandit sed libero. Nam at erat sit amet nisi suscipit iaculis. Cras quis eros tellus, et
                mattis augue. Proin dictum urna sed dui pulvinar dictum. Mauris sit amet lectus ac ante volutpat
                volutpat. Nullam et mauris tempus libero iaculis dignissim non vitae nunc. Morbi iaculis dapibus augue
                ac ultrices. Sed vitae massa hendrerit augue egestas sagittis id quis massa. Sed sem massa, imperdiet
                viverra aliquet in, porta ac sapien. Nunc scelerisque mi nec erat eleifend ac porta eros rutrum. Sed et
                eros vitae augue lacinia mattis in id quam. Aenean ipsum sem, ullamcorper porttitor tempus ac, porta et
                lectus. Praesent et ornare dui.
            </div>
        </div>

        <div class="wall-message">
            <div class="wall-message-head">
                <a href="" title="имя отправителя">имя отправителя</a>
                &nbsp|&nbsp
                <a href="" title="удалить">удалить</a>
            </div>
            <div class="wall-message-text">
                Sed nec cursus nibh. Pellentesque fringilla vestibulum urna, in vulputate nibh tincidunt et. Aenean
                tempus blandit tortor ut cursus. Quisque sed nulla et odio consectetur fringilla vel at nunc. Phasellus
                tempus nunc vitae elit tempus convallis. Curabitur blandit bibendum nulla, vel dignissim magna auctor
                id. Curabitur volutpat condimentum tempus. Integer sit amet justo eget nisl posuere consequat. Nunc
                pretium eleifend dapibus. Vivamus id tristique lectus.
            </div>
        </div>

        <div class="wall-message">
            <div class="wall-message-head">
                <a href="" title="имя отправителя">имя отправителя</a>
                &nbsp|&nbsp
                <a href="" title="удалить">удалить</a>
            </div>
            <div class="wall-message-text">
                Suspendisse convallis massa eget libero iaculis posuere. Proin et nunc elit. Phasellus tristique
                dignissim neque at feugiat. Proin pharetra massa nec erat elementum id auctor tortor tempor. Aliquam
                magna purus, vestibulum sit amet mollis a, tincidunt at leo. Nulla ullamcorper tincidunt varius. Donec
                euismod quam quis lacus laoreet aliquam. Fusce nec eros orci. Nam ultricies nibh a turpis convallis
                dapibus. Fusce felis ligula, viverra a sagittis ut, pretium in sem. Fusce dignissim vulputate purus, non
                posuere arcu lobortis id. Sed ut libero est, ut suscipit nisi. In posuere placerat fermentum. Aliquam
                euismod faucibus libero, ac condimentum leo congue et. Nunc suscipit fermentum ipsum id dapibus.
                Curabitur lorem arcu, scelerisque id adipiscing at, molestie sit amet massa. Aliquam erat volutpat.
                Etiam euismod lacinia mauris a accumsan.
            </div>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>