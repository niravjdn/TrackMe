<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>TrackMe</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/logo_small.PNG" />
<style>
    .btn2{
            position: relative;
            display: inline-block;
            padding: 6px 12px;
            font-size: 13px;
            font-weight: bold;
            line-height: 20px;
            color: #333;
            white-space: nowrap;
            vertical-align: middle;
            cursor: pointer;
            background-color: #EEE;
            background-image: linear-gradient(#FCFCFC, #EEE);
            border: 1px solid #D5D5D5;
            border-radius: 3px;
            -moz-user-select: none;
    }
</style>
</head>
<body background="images/bg_trackme.jpg">
<jsp:include page="header.html"></jsp:include>
<%@page import="javax.servlet.http.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.awt.*" %>
<%@page  import="java.awt.datatransfer.*" %>
<%
    String msg = (String)session.getAttribute("login");
    
    if(msg=="yes")
    {
    %>
    <a href="managecourier.jsp" >Manage Couriers</a>
    <form action="LogoutServlet" method="post" ><input type="submit" class="myButton"  value="Logout" style="float:right;"/></form>

    <% }
    else
    {%>
    <a href="login-reg.html"  ><input type="button"  class="myButton" value="Login/Register" style="float:right;"/></a>
    
    <% }%>
<br/>
<center>

 <form name="couriertrack" action="track.jsp" method="post">
    <br>
	Now,Track Your Item From Anywhere,AnyTime...
	<table>
    <tr><td align="right" >
           <%! String mycc="";String link="";String tinput="";String cc="";%>
		Company:</td><td>
                    <select name="cc" class="city" style="padding:10%;" >
                        <%
                          String url="jdbc:derby://localhost:1527/Nirav";
                          String username="Nirav";
                          String pwd="abc";
                          String showmyprev="";
                          ResultSet rs= null;
                          Connection cn = null;
                          Statement stmnt = null;
                          String ps = "default";
                          cc = request.getParameter("cname");
                          tinput = request.getParameter("awb");
                          if(cc==null)
                          {
                              session.setAttribute("prevsel",request.getParameter("cc"));
                              tinput="";
                          }
                          else
                              session.setAttribute("prevsel",cc);
                                                       
                          
                    try
                     {

                       Class.forName("org.apache.derby.jdbc.ClientDriver");
                        cn = DriverManager.getConnection(url,username,pwd);
                        String query = "";
                        
                       query = "select cc from Nirav.COURIERLINK";
                        stmnt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);                      
                       rs= stmnt.executeQuery(query);
                       String id = "";
                       if(session.getAttribute("prevsel")!=null)
                              ps=(String)session.getAttribute("prevsel");
                       else
                       {
                           rs.next();
                           ps = rs.getString("cc");
                           rs.previous();
                       }
                        
                        %> 
                  
                       
                     <%  while(rs.next()){
                         id=rs.getString("cc");
                         
                         if(ps.equals(id))
                         {
                        %>
                         
                        <option value="<%= id %>" selected="selected"><%= id %></option>
                      
                      <% }else{ %>
                      <option value="<%= id %>" ><%= id %></option>
                     
                    <% }}  %>
                       </select>
                  
                     
                     <%
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
                     if(msg=="yes")//&& (!(request.getParameter("trackingno").isEmpty())))
                        {
                         
                                String tn = request.getParameter("trackingno");
                                String email = (String)session.getAttribute("uname");
                                String query = "";
                                
                                if(tn!=null && tn.length()>0)
                                {   //update here
                                    
                                    String company =request.getParameter("cc");
                                     query= "update Nirav.CMS_HISTORY set company='"+company+"',awb='"+tn+"' where email='"+email+"' ";
                                    stmnt = cn.createStatement();   
                                    stmnt.executeUpdate(query);                                    
                                }
                                else
                                {
                                    link = link.concat(tinput);
                                    query= "update Nirav.CMS_HISTORY set company='"+cc+"',awb='"+tinput+"' where email='"+email+"' ";
                                    stmnt = cn.createStatement();   
                                    stmnt.executeUpdate(query);  
                                }
                                query= "select * from Nirav.CMS_HISTORY where email='"+email+"' ";
                                stmnt = cn.createStatement();   
                                    rs = stmnt.executeQuery(query);
                                    if(rs.next())
                                    {
                                        mycc= rs.getString("company");
                                    showmyprev = rs.getString("awb");
                                    }
                        }
                    %>
                    
    	</select></td></tr>
		</table>
      
        <table><tr><td style="overflow:hidden;">
                    <input type="text" id="foo" name="trackingno" placeholder="Enter Item number to track..........." style="padding:3%;" size="70"  class="city" autofocus value="<%= tinput %>"/>
    </td><td>
        <button name="search" class="search_btn"  onclick="form.action='track.jsp'; "><img src="images/search1.png" height="50px" width="50px"/></button></form>
        
    </td>
    </tr>
    </table>
    </form>

<%
    if((request.getParameter("cc")!=null))
      cc = request.getParameter("cc");
    
    
        link = "welcome.html";
    try
         {
             
           Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(url,username,pwd);
           String query = "select * from Nirav.COURIERLINK where cc=? ";
           PreparedStatement pst = cn.prepareStatement(query);
           pst.setString(1,cc);
           rs= pst.executeQuery();
           if(rs.next())
           {
           link=rs.getString("tlink");
           if(rs.getBoolean("append"))
               if(request.getParameter("trackingno")!=null)//for its own request
                link = link.concat(request.getParameter("trackingno"));
           else
                link = link.concat(tinput);
           }
         
          
           pst.close();
           cn.close();
           rs.close();
         
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
%>
<br/>

<% if(showmyprev.equals("0")){ %>
    You don't have any previous search history!!!
<%} else if(showmyprev.length()>0) { %>
Your Latest Search is : <%= mycc %> -><b id="cpy"> <%= showmyprev %> </b> <button class="btn2" data-clipboard-action="copy" data-clipboard-target="#cpy"><img style=".clippy {margin-top: -3px;position: relative;top: 3px;}" src="images/clippy.svg" width="13" alt="Copy to clipboard"></button>
<% } %>

<br/><br/>
<script src="js/clipboard.min.js"></script>
<script>
    var clipboard = new Clipboard('.btn2');

    clipboard.on('success', function(e) {
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
</script>

<iframe id="ifrm" src="<%= link %>" style="width: 1360px; height: 900px; overflow: visible;"></iframe>
</center>
    <jsp:include page="footer.html"></jsp:include>
</body>
</html>
