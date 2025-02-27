import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
public class Main {
    public static void main(String[] args)throws Exception {
        System.out.printf("Hello \n welcome in our DataBase with java"); 
    String url="jdbc:postgresql://localhost:5432/MyDataBase";
    String username="postgres";
    String password="1234";
        Connection con=DriverManager.getConnection(url,username,password);
        System.out.println("Connect to the DB Successfully");
   // Statement s=;  the first case (statement as interface)
    // CallableStatement the second case
    //PreparedStatement the third case
    //Statement using to Execute Query from DB (SELECTs)
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select fname from employee");
        System.out.println("The result is sotred successfully");
    //now rs has the data of the selecting from db
        rs.next();
       // String name=rs.getString(1);
     //   System.out.println(name);
      //  int i=1;
       while(rs.next())
       {
           String name=rs.getString(1);
           System.out.println(name);
          // i++;
       }
        rs.close();
        st.close();
        con.close();
    }
}
