
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(urlPatterns = {"/InsertCourier"})
public class InsertCourier extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
         String url="jdbc:derby://localhost:1527/Nirav";
         String username="Nirav";
         String pwd="abc";
         HttpSession session = request.getSession(false);
         String email =(String) session.getAttribute("uname");
         String dest = "/managecourier.jsp";
         if(request.getParameter("awb").isEmpty() || request.getParameter("cc").isEmpty())  
         {
             session.setAttribute("alertmsg","true") ;
//             out.println("<script type='text/javascript'>");
//            out.println("prompt('AWB Number and Company name must not be empty!!!')");
//            out.println("</script>");
//               
         }
         else
         {
         try
         {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           Connection cn = DriverManager.getConnection(url,username,pwd);
           String query = "insert into Nirav.MAIN_RECORD values(?,?,?,?,?,?)";
           PreparedStatement pst = cn.prepareStatement(query);
           pst.setString(1,email);
           pst.setString(2,request.getParameter("awb"));
           pst.setString(3, request.getParameter("cc"));
           pst.setString(4, request.getParameter("desc"));
           pst.setTimestamp(5, getCurrentTimeStamp());
           boolean b=false;
           if(request.getParameter("received").equalsIgnoreCase("received"))
               b=true;
           pst.setBoolean(6,b);
           pst.executeUpdate();
                      
           pst.close();
           cn.close();
          
         
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
         }
         response.sendRedirect(request.getContextPath() + dest);
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
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());
    }

}
