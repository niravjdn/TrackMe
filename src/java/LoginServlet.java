
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email=request.getParameter("email");
	String password=request.getParameter("password");
        String url="jdbc:derby://localhost:1527/Nirav";
        String username="Nirav";
        String pwd="abc";
        ResultSet rs ;       
                
        try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           Connection cn = DriverManager.getConnection(url,username,pwd);
           Statement stmnt = cn.createStatement();
           String query = "select * from Nirav.CMS_USER where email='"+email+"' and password='"+password+"'";
           rs = stmnt.executeQuery(query);
            HttpSession session;
           if(rs.next())
           {
               session = request.getSession();
               session.setAttribute("login", "yes");
               session.setAttribute("alertmsg", "false");
               session.setAttribute("uname", email);
               session.setAttribute("msg","Hello,"+rs.getString("name"));
               response.sendRedirect(request.getContextPath() + "/index.jsp");
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
               rd.include(request, response);
               out.println("Sorry! Username or Password Error. plz Enter Correct Detail or Register");
               
           }
           
           stmnt.close();
           cn.close();
           rs.close();
           
       }
        catch(ClassNotFoundException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
            rd.include(request, response);
            out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
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
