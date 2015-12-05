<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>TrackMe</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/logo_small.PNG" />
<style type="text/css">
.btnstyle
{
	background:#11C8E0;
	padding:2px 5px;
}
</style>
</head>
<body background="images/bg_trackme.jpg">
    <jsp:include page="header.html"></jsp:include>
<%@page import="javax.servlet.http.*" %>
<%
    String msg = (String)session.getAttribute("login");
    if(msg=="yes")
    {
    %>
    <a href="managecourier.jsp" >Manage Couriers</a>
    <form action="LogoutServlet" method="post" ><input type="submit"  class="myButton" value="Logout" style="float:right;"/></form>

    <% }
    else
    {%>
    <a href="login-reg.html"  ><input type="button" class="myButton" value="Login/Register" style="float:right;"/></a>
    <% }%>
<br/>
<center>
<div class="section">
 <form name="couriertrack" action="track.jsp" method="post">
    <%
     msg = (String)session.getAttribute("msg");
    if(msg!=null)
    {
    %>
    ${msg}
    <% }%>


    <br>
	Now,Track Your Item From Anywhere,AnyTime...
	

        <br/><br/><br/>
        <button name="search" class="myButton" onclick="form.action='track.jsp';">Want to track your courier?</button></form>
    </form>
</div>
</center>
    <jsp:include page="footer.html"></jsp:include>
    
</body>
</html>
