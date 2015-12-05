<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>TrackMe</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/logo_small.PNG" />
<link rel="stylesheet" type="text/css" href="css/style_jquery.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script src="js/jquery.autocomplete.js"></script>
<style>
    label { display:inline-block; vertical-align:central; }
	textarea { display:inline-block; vertical-align:middle; }
	.tableheader { background:#0A4F8B; }
        a:link,a:visited
        {
            color: #ffffff;
            text-decoration: underline;
        }
        a:hover 
        {
            color: #ff99ff;
        }
        a:active
        {
            color: #3399ff;
        }
</style>
</head>
<body background="images/bg_trackme.jpg" >
    <jsp:include page="header.html"></jsp:include>
<%@page import="javax.servlet.http.*" %>

<%@page import="javax.servlet.*" %>
<%@page import="java.sql.*" %>

        <%@page import="java.util.*" %>
<%
    String msg = (String)session.getAttribute("login");
   
    if(msg=="yes")
    {
    %>
    <a href="managecourier.jsp" >Manage Couriers</a>
    <form action="LogoutServlet" method="post" ><input type="submit" class="myButton" value="Logout" style="float:right;"/></form>

    <% }
    else
    {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    %>    
    <% }%>
   
    <% 
       if(session.getAttribute("alertmsg").equals("true"))
       {
        session.setAttribute("alertmsg","false");
    %>
    <script type="text/javascript">
        alert("AWB Number and Company name can't be empty");
    </script>
  
    <% } %>
                  
<br/>
<form name="main_form"  method="post">
<table width="100%" style="border:1px solid white;"><tr>
        <td class="tableheader">Select</td>
        <td class="tableheader">AWB Number</td>
        <td class="tableheader">Courier Company</td>
        <td class="tableheader">Description</td>
        <td class="tableheader">TimeStamp</td>
        <td class="tableheader">Delivered or not</td>
    </tr>
    <%
                String url="jdbc:derby://localhost:1527/Nirav";
                String username="Nirav";
                String pwd="abc";
                ResultSet rs = null;
                String email="";
                Connection cn = null;
                Statement stmnt = null;
      try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           cn = DriverManager.getConnection(url,username,pwd);
           stmnt = cn.createStatement();
           email=(String)session.getAttribute("uname");
           String query = "select * from Nirav.MAIN_RECORD where email='"+email+"' order by timestamp desc";
           rs = stmnt.executeQuery(query);                 
           
       }
        catch(ClassNotFoundException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("error_page.jsp");
            rd.include(request, response);
            out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("error_page.jsp");
            rd.include(request, response);
            out.println(e.getMessage());
        }
        finally
        {
            
        }

    %>
   <!--while loop-->
    <%
      while(rs.next())
      {
          String ts = rs.getString("timestamp");
          int i = ts.lastIndexOf('.');
     %>
      <tr>
          <td><input type="checkbox" name="selection" value="<%= rs.getTimestamp("timestamp").toString() %>"/></td>
      <td>
          
          <%
  
    String cc=rs.getString("company");   //rs of main record
    String link = "welcome.html";
    ResultSet rs2 = null;
    boolean bb=false;
    try
         {
             
           Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(url,username,pwd);
           String query = "select * from Nirav.COURIERLINK where cc=? ";
           PreparedStatement pst = cn.prepareStatement(query);
           pst.setString(1,cc);
           rs2= pst.executeQuery();
           if(rs2.next())
           {
                link=rs2.getString("tlink");
                if(rs2.getBoolean("append"))
                link = link.concat(rs.getString("awb"));
               bb = true;
           }         
          
           pst.close();
           cn.close();
          
         
       }
        catch(ClassNotFoundException e)
        {
           // RequestDispatcher rd=request.getRequestDispatcher("error_page.jsp");
            //rd.include(request, response);
            out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            //RequestDispatcher rd=request.getRequestDispatcher("error_page.jsp");
           // rd.include(request, response);
            out.println(e.getMessage());
        }
        finally
        {
           
        }
    if(bb)
{
          String awb=rs.getString("awb");
          String target = "track.jsp?cname="+cc+"&awb="+awb+" ";
          
%>
        
<a href="<%= target %>" > <%= awb  %>      </a>
<% }else{ %>
<%= rs.getString("awb") %>
<% } %>
      </td>
      <td><%= rs.getString("company") %></td>
      <td><%= rs.getString("description") %></td>
      <td><%= ts.substring(0, i ) %></td>
      <%
      boolean b = rs.getBoolean("delivered");  
      if(b){
      %>
      <td><input type="checkbox"  name="delivered" checked="checked" value="<%= rs.getTimestamp("timestamp").toString() %>"/></td>
      <%
        }else{          
      %>
      <td><input type="checkbox"  name="delivered" value="<%= rs.getTimestamp("timestamp").toString() %>"/></td>
      <%
        }}
       %>
      </tr>
      <!--close others-->
       <%
           stmnt.close();
           cn.close();
           rs.close();
      %>
    
</table>
<br/>
<p>
      <center >
          
          <input style="margin-left: 35%;float:left;"  type="submit" value="Save State" class="btn" onclick="form.action='SaveStateServlet';"/>
          
          <input type="submit" style="margin-right: 35%" value="Delete Selected" onclick="form.action='DeleteSelectedServlet';" class="btn"/>
         
      </center>
    </form>
</pre>
      
<fieldset > 	
    <legend>Insert New Record</legend>
    <form method="post" action="InsertCourier">
   AWB Number : <input type="text" name="awb" placeholder="Enter AWB number here"/><br/>
   Courier Company : <input type="text" class="inputtext" id="cc" name="cc" placeholder="Enter Courier Company name here"/><br/><br/>
   <div class="va"><label>Description : </label> <textArea  rows="5" cols="25" name="desc" placeholder="Enter your description here"></textarea></div><br/>
   Received : <input type="radio" name="received" value="received"/><br/>
  Not Received :  <input type="radio" name="received" value="not received" checked="checked"/><br/>
<center><input type="submit" value="insert" class="btn"/></center>
</form>
</fieldset>

<footer>
    <jsp:include page="footer.html"></jsp:include>
</footer>
<script>
jQuery(function(){
$("#cc").autocomplete("list.jsp");
});
</script> 

</body>
</html>
