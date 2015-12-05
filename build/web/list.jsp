<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
 
   <%
   try{
     String s[]=null;
 
      String url="jdbc:derby://localhost:1527/Nirav";
      String username="Nirav";
      String pwd="abc";
      ResultSet rs ;       
       
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           Connection cn = DriverManager.getConnection(url,username,pwd);
           Statement stmnt = cn.createStatement();
           rs = stmnt.executeQuery("select * from Nirav.COURIERLINK");
 
       List li = new ArrayList();
 
       while(rs.next())
       {
           li.add(rs.getString(1));
       }
 
       String[] str = new String[li.size()];
       Iterator it = li.iterator();
 
       int i = 0;
       while(it.hasNext())
       {
           String p = (String)it.next();
           str[i] = p;
           i++;
       }
 
    //jQuery related start
       String query = (String)request.getParameter("q");
 
       int cnt=1;
       for(int j=0;j<str.length;j++)
       {
           if(str[j].toUpperCase().startsWith(query.toUpperCase()))
           {
              out.print(str[j]+"\n");
              if(cnt>=5)// 5=How many results have to show while we are typing(auto suggestions)
              break;
              cnt++;
            }
       }
    //jQuery related end
 
rs.close();
stmnt.close();
cn.close();
 
}
catch(Exception e){
e.printStackTrace();
}
 %>