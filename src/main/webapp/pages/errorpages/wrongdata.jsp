<%
    String requestMessage = (String) request.getAttribute("message");
    if (requestMessage != null) {
        session.setAttribute("message", requestMessage);
        response.sendRedirect(request.getContextPath() + "/pages/errorpages/wrongdata.jsp");
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <link rel="stylesheet" type="text/css" href="../../css/commonstyle.css" />
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