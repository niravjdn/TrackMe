package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<style type=\"text/css\">\n");
      out.write(".btnstyle\n");
      out.write("{\n");
      out.write("\tbackground:#11C8E0;\n");
      out.write("\tpadding:2px 5px;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body background=\"images/bg_trackme.jpg\">\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.html", out, false);
      out.write('\n');
      out.write('\n');

    String msg = (String)session.getAttribute("login");
    if(msg=="yes")
    {
    
      out.write("\n");
      out.write("    <a href=\"managecourier.jsp\" >Manage Couriers</a>\n");
      out.write("    <form action=\"LogoutServlet\" method=\"post\" ><input type=\"submit\"  class=\"myButton\" value=\"Logout\" style=\"float:right;\"/></form>\n");
      out.write("\n");
      out.write("    ");
 }
    else
    {
      out.write("\n");
      out.write("    <a href=\"login-reg.html\"  ><input type=\"button\" class=\"myButton\" value=\"Login/Register\" style=\"float:right;\"/></a>\n");
      out.write("    ");
 }
      out.write("\n");
      out.write("<br/>\n");
      out.write("<center>\n");
      out.write("<div class=\"section\">\n");
      out.write(" <form name=\"couriertrack\" action=\"track.jsp\" method=\"post\">\n");
      out.write("    ");

     msg = (String)session.getAttribute("msg");
    if(msg!=null)
    {
    
      out.write("\n");
      out.write("    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${msg}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("    ");
 }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    <br>\n");
      out.write("\tNow,Track Your Item From Anywhere,AnyTime...\n");
      out.write("\t\n");
      out.write("\n");
      out.write("        <br/><br/><br/>\n");
      out.write("        <button name=\"search\" class=\"myButton\" onclick=\"form.action='track.jsp';\">Want to track your courier?</button></form>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("</center>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.html", out, false);
      out.write("\n");
      out.write("    \n");
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
