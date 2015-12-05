package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public final class managecourier_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      			"error_page.jsp", true, 8192, true);
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
      out.write("<style>\n");
      out.write("    label { display:inline-block; vertical-align:central; }\n");
      out.write("\n");
      out.write("textarea { display:inline-block; vertical-align:middle; }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body background=\"images/bg_trackme.jpg\">\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.html", out, false);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        \n");

    String msg = (String)session.getAttribute("login");
   
    if(msg=="yes")
    {
    
      out.write("\n");
      out.write("    <a href=\"managecourier.jsp\" >Manage Couriers</a>\n");
      out.write("    <form action=\"LogoutServlet\" method=\"post\" ><input type=\"submit\" value=\"Logout\" style=\"float:right;\"/></form>\n");
      out.write("\n");
      out.write("    ");
 }
    else
    {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    
      out.write("    \n");
      out.write("    ");
 }
      out.write("\n");
      out.write("   \n");
      out.write("    ");
 
       if(session.getAttribute("alertmsg").equals("true"))
       {
        session.setAttribute("alertmsg","false");
    
      out.write("\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("        alert(\"AWB Number and Company name can't be empty\");\n");
      out.write("    </script>\n");
      out.write("  \n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("                  \n");
      out.write("<br/>\n");
      out.write("<form name=\"main_form\"  method=\"post\">\n");
      out.write("<table width=\"100%\" border=\"2\">\n");
      out.write("    <tr>\n");
      out.write("        <th>Select</th>\n");
      out.write("        <th>AWB Number</th>\n");
      out.write("        <th>Courier Company</th>\n");
      out.write("        <th>Description</th>\n");
      out.write("        <th>TimeStamp</th>\n");
      out.write("        <th>Delivered or not</th>\n");
      out.write("    </tr>\n");
      out.write("    ");

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

    
      out.write("\n");
      out.write("   <!--while loop-->\n");
      out.write("    ");

      while(rs.next())
      {
          String ts = rs.getString("timestamp");
          int i = ts.lastIndexOf('.');
     
      out.write("\n");
      out.write("      <tr>\n");
      out.write("          <td><input type=\"checkbox\" name=\"selection\" value=\"");
      out.print( rs.getTimestamp("timestamp").toString() );
      out.write("\"/></td>\n");
      out.write("      <td>");
      out.print( rs.getString("awb") );
      out.write("</td>\n");
      out.write("      <td>");
      out.print( rs.getString("company") );
      out.write("</td>\n");
      out.write("      <td>");
      out.print( rs.getString("description") );
      out.write("</td>\n");
      out.write("      <td>");
      out.print( ts.substring(0, i ) );
      out.write("</td>\n");
      out.write("      ");

      boolean b = rs.getBoolean("delivered");  
      if(b){
      
      out.write("\n");
      out.write("      <td><input type=\"checkbox\"  name=\"delivered\" checked=\"checked\" value=\"");
      out.print( rs.getTimestamp("timestamp").toString() );
      out.write("\"/></td>\n");
      out.write("      ");

        }else{          
      
      out.write("\n");
      out.write("      <td><input type=\"checkbox\"  name=\"delivered\" value=\"");
      out.print( rs.getTimestamp("timestamp").toString() );
      out.write("\"/></td>\n");
      out.write("      ");

        }}
       
      out.write("\n");
      out.write("      </tr>\n");
      out.write("      <!--close others-->\n");
      out.write("       ");

           stmnt.close();
           cn.close();
           rs.close();
      
      out.write("\n");
      out.write("    \n");
      out.write("</table>\n");
      out.write("<br/>\n");
      out.write("<p>\n");
      out.write("      <center >\n");
      out.write("          \n");
      out.write("          <input style=\"margin-left: 35%;float:left;\"  type=\"submit\" value=\"Save State\" class=\"btn\" onclick=\"form.action='SaveStateServlet';\"/>\n");
      out.write("          \n");
      out.write("          <input type=\"submit\" style=\"margin-right: 35%\" value=\"Delete Selected\" onclick=\"form.action='DeleteSelectedServlet';\" class=\"btn\"/>\n");
      out.write("         \n");
      out.write("      </center>\n");
      out.write("    </form>\n");
      out.write("</pre>\n");
      out.write("      \n");
      out.write("<fieldset > \t\n");
      out.write("    <legend>Insert New Record</legend>\n");
      out.write("    <form method=\"post\" action=\"InsertCourier\">\n");
      out.write("   AWB Number : <input type=\"text\" name=\"awb\" placeholder=\"Enter AWB number here\"/><br/>\n");
      out.write("   Courier Company : <input type=\"text\" name=\"cc\" placeholder=\"Enter Courier Company name here\"/><br/><br/>\n");
      out.write("   <div class=\"va\"><label>Description : </label> <textArea  rows=\"5\" cols=\"25\" name=\"desc\" placeholder=\"Enter your description here\"></textarea></div><br/>\n");
      out.write("   Received : <input type=\"radio\" name=\"received\" value=\"received\"/><br/>\n");
      out.write("  Not Received :  <input type=\"radio\" name=\"received\" value=\"not received\" checked=\"checked\"/><br/>\n");
      out.write("<center><input type=\"submit\" value=\"insert\" class=\"btn\"/></center>\n");
      out.write("</form>\n");
      out.write("</fieldset>\n");
      out.write("\n");
      out.write("<footer>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.html", out, false);
      out.write("\n");
      out.write("</footer>\n");
      out.write("    \n");
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
