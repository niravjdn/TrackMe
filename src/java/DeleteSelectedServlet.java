
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/DeleteSelectedServlet"})
public class DeleteSelectedServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
		PrintWriter out=response.getWriter();
                
                HttpSession session = request.getSession(false);
                if(session==null)
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                String[] mul = request.getParameterValues("selection");
                
                String url="jdbc:derby://localhost:1527/Nirav";
                String username="Nirav";
                String pwd="abc";
                
                String email = (String)session.getAttribute("uname");
                Connection cn = null;
                PreparedStatement pst = null;
                
                    
                try
               {
                   Class.forName("org.apache.derby.jdbc.ClientDriver");
                    cn = DriverManager.getConnection(url,username,pwd);
                    String query = "delete from Nirav.MAIN_RECORD  where email=? and timestamp=?";
                    pst = cn.prepareStatement(query);                  
                  if(mul!=null)
                  {
                   for(int i=0;i<mul.length;i++)
                     {
                         Timestamp ts = (Timestamp.valueOf(mul[i]));
                         pst.setString(1, email);
                         pst.setTimestamp(2, ts);
                         pst.executeUpdate();                         
                     }
                  }
                   response.sendRedirect(request.getContextPath() + "/managecourier.jsp");                    
                   pst.close();
                   cn.close();                       
               }
                catch(ClassNotFoundException e)
                {
                    RequestDispatcher rd=request.getRequestDispatcher("managecourier.jsp");
                    rd.include(request, response);
                    out.println(e.getMessage());
                }
                catch(SQLException e)
                {
                    RequestDispatcher rd=request.getRequestDispatcher("managecourier.jsp");
                    rd.include(request, response);
                    out.println(e.getMessage());
                }

    }
                
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
