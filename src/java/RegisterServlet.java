
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;


@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
		PrintWriter out=response.getWriter();
                String name=request.getParameter("name");
		String email=request.getParameter("email");
                String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String addressLine=request.getParameter("addressLine");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String contact1 = request.getParameter("contact");
                double contact=0;
                if(!contact1.isEmpty())
                    contact = Double.parseDouble(contact1);
                String url="jdbc:derby://localhost:1527/Nirav";
                String username="Nirav";
                String pwd="abc";
                
        try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           Connection cn = DriverManager.getConnection(url,username,pwd);
           Statement stmnt = cn.createStatement();
           String query = "insert into Nirav.CMS_USER values('"+name+"','"+email+"','"+password+"','"+gender+"','"+dob+"','"+addressLine+"','"+city+"','"+state+"','"+country+"',"+contact+")";
           if(name.isEmpty() || email.isEmpty() || password.isEmpty())
           {
               RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
               rd.include(request, response);
               out.println("Name,Email and password are compulsary field!!!");
               return;
           }
           stmnt.executeUpdate(query);
           query = "insert into Nirav.CMS_HISTORY values ('"+email+"','blank','0')";
           stmnt.executeUpdate(query);
           
           //RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
           
           HttpSession session = request.getSession();
           session.setAttribute("msg","Registration Successful!!!");
           session.setAttribute("name",name);
           
           stmnt.close();
           cn.close();
           response.sendRedirect(request.getContextPath() + "/index.jsp");
       }
        catch(ClassNotFoundException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
            out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            RequestDispatcher rd=request.getRequestDispatcher("login-reg.html");
            rd.include(request, response);
            out.println(e.getMessage());
        }
        finally
        {
            
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
