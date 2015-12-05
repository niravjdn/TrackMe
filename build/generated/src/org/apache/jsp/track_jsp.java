package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.sql.*;
import java.awt.*;
import java.awt.datatransfer.*;

public final class track_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("<title>TrackMe</title>\n");
      out.write("<link href=\"style/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<link rel=\"shortcut icon\" href=\"images/logo_small.PNG\" />\n");
      out.write("</head>\n");
      out.write("<body background=\"images/bg_trackme.jpg\">\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.html", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    String msg = (String)session.getAttribute("login");
    
    if(msg=="yes")
    {
    
      out.write("\n");
      out.write("    <a href=\"managecourier.jsp\" >Manage Couriers</a>\n");
      out.write("    <form action=\"LogoutServlet\" method=\"post\" ><input type=\"submit\" class=\"myButton\"  value=\"Logout\" style=\"float:right;\"/></form>\n");
      out.write("\n");
      out.write("    ");
 }
    else
    {
      out.write("\n");
      out.write("    <a href=\"login-reg.html\"  ><input type=\"button\"  class=\"myButton\" value=\"Login/Register\" style=\"float:right;\"/></a>\n");
      out.write("    \n");
      out.write("    ");
 }
      out.write("\n");
      out.write("<br/>\n");
      out.write("<center>\n");
      out.write("\n");
      out.write(" <form name=\"couriertrack\" action=\"track.jsp\" method=\"post\">\n");
      out.write("    <br>\n");
      out.write("\tNow,Track Your Item From Anywhere,AnyTime...\n");
      out.write("\t<table>\n");
      out.write("    <tr><td align=\"right\" >\n");
      out.write("           \n");
      out.write("\t\tCompany:</td><td>\n");
      out.write("                    <select name=\"cc\" class=\"city\" style=\"padding:10%;\" >\n");
      out.write("                        ");

                          String url="jdbc:derby://localhost:1527/Nirav";
                          String username="Nirav";
                          String pwd="abc";
                          String showmyprev="";
                          ResultSet rs= null;
                          Connection cn = null;
                          Statement stmnt = null;
                          String ps = "default";
                          session.setAttribute("prevsel",request.getParameter("cc"));
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
                        
                        
      out.write(" \n");
      out.write("                       ");
      out.print(ps);
      out.write("\n");
      out.write("                     ");
  while(rs.next()){
                         id=rs.getString("cc");
                         
                         if(ps.equals(id))
                         {
                        
      out.write("\n");
      out.write("                         \n");
      out.write("                        <option value=\"");
      out.print( id );
      out.write("\" selected=\"selected\">");
      out.print( id );
      out.write("</option>\n");
      out.write("                      \n");
      out.write("                      ");
 }else{ 
      out.write("\n");
      out.write("                      <option value=\"");
      out.print( id );
      out.write("\" >");
      out.print( id );
      out.write("</option>\n");
      out.write("                     \n");
      out.write("                    ");
 }}  
      out.write("\n");
      out.write("                       </select>\n");
      out.write("                     \n");
      out.write("                     ");

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
                                String tn = request.getParameter("cc");
                                String email = (String)session.getAttribute("uname");
                                String query = "";
                                
                                if(tn!=null && tn.length()>0)
                                {   //update here
                                    tn = tn.concat(" -> ");
                                    tn = tn.concat(request.getParameter("trackingno"));
                                     query= "update Nirav.CMS_HISTORY set awb='"+tn+"' where email='"+email+"' ";
                                    stmnt = cn.createStatement();   
                                    stmnt.executeUpdate(query);                                    
                                }
                                query= "select * from Nirav.CMS_HISTORY where email='"+email+"' ";
                                stmnt = cn.createStatement();   
                                    rs = stmnt.executeQuery(query);
                                    if(rs.next())
                                    showmyprev = rs.getString("awb");
                        }
                    
      out.write("\n");
      out.write("                    \n");
      out.write("    \t</select></td></tr>\n");
      out.write("\t\t</table>\n");
      out.write("\n");
      out.write("        <table><tr><td style=\"overflow:hidden;\">\n");
      out.write("    <input type=\"text\" id=\"foo\" name=\"trackingno\" placeholder=\"Enter Item number to track...........\" style=\"padding:3%;\" size=\"70\"  class=\"city\" autofocus />\n");
      out.write("    </td><td>\n");
      out.write("        <button name=\"search\" class=\"search_btn\"  onclick=\"form.action='track.jsp'; \"><img src=\"images/search1.png\" height=\"50px\" width=\"50px\"/></button></form>\n");
      out.write("        \n");
      out.write("    </td>\n");
      out.write("    </tr>\n");
      out.write("    </table>\n");
      out.write("    </form>\n");

  
    String cc=request.getParameter("cc");
   
    String link = "welcome.html";
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
           link = link.concat(request.getParameter("trackingno"));
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

      out.write("\n");
      out.write("<br/>\n");
 if(showmyprev.equals("0")){ 
      out.write("\n");
      out.write("    You don't have any previous search history!!!\n");
} else if(showmyprev.length()>0) { 
      out.write("\n");
      out.write("    Your Latest Search is : ");
      out.print( showmyprev );
      out.write('\n');
 } 
      out.write("\n");
      out.write("<br/><br/>\n");
      out.write("<iframe id=\"ifrm\" src=\"");
      out.print( link );
      out.write("\" style=\"width: 1360px; height: 900px; overflow: visible;\"></iframe>\n");
      out.write("</center>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.html", out, false);
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
