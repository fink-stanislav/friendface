<%--
  Created by IntelliJ IDEA.
  User: sfink
  Date: 1/20/11
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test='${param.headerType == "oneRef"}'>
        <div id="header">
            <div id="headertxt"><a href="/friendface" title="главная страница">friendface</a> - ${param.title}</div>
            <div id="headrefs">
                <a href=${param.rightReference} title=${param.rightReferenceName}> ${param.rightReferenceName}</a>
            </div>
        </div>
    </c:when>

    <c:when test='${param.headerType == "twoRefs"}'>
        <div id="header">
            <div id="headertxt"><a href="/friendface" title="главная страница">friendface</a></div>
            <div id="headrefs">
                <a href=${param.leftReference} title=${param.leftReferenceName}> ${param.leftReferenceName}</a>
                <a href=${param.rightReference} title=${param.rightReferenceName}> ${param.rightReferenceName}</a>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div id="header">
            <div id="headertxt"><a href="/friendface" title="главная страница">friendface</a> - ${param.title}</div>
        </div>
    </c:otherwise>
</c:choose>