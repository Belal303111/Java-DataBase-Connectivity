import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter your name:");
        String user=s.nextLine();
        System.out.print("Enter your task:");
        String task=s.nextLine();
//        System.out.print("Enter your new task:");
//        String new_task=s.nextLine();
//        System.out.print("Enter your new description of this task:");
//        String description=s.nextLine();
//        System.out.print("Are you finished your task(true of false):");
//        boolean status=s.nextBoolean();
        ToDo Tasks=new ToDo();
        Tasks.DeleteTask(user,task);
    //    Tasks.AddTask(user,task,description,status);
//        System.out.print("Enter your name:"); //to functions read and iscompelet
//        String name=s.nextLine();
//        Tasks.ReadTask(name);

    }
}
class ToDo {
    String url="jdbc:postgresql://localhost:5432/ToDo";
    String user="postgres";
    String password="1234";
    public void AddTask(String u,String t,String d,boolean s)throws Exception{  //insert into table
        //u ==> username,t==>task , d==>description , s==> status
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement st=con.prepareStatement("insert into tasks(username,task,description,status) values(?,?,?,?)");
        //preparedStatement
        st.setString(1,u);
        st.setString(2,t);
        st.setString(3,d);
        st.setBoolean(4,s);
        st.executeUpdate(); //using in insert , delete , updata
        //this instruction important to execute the update (without it the code that was written don't execute)
        st.close();
        con.close();
    }
    public void isComplete(String u)throws Exception{
        Connection con=DriverManager.getConnection(url,user,password);
       // Statement st=con.createStatement();
        PreparedStatement st =con.prepareStatement("select status from tasks where username=?");
        st.setString(1,u);
        ResultSet rs=st.executeQuery();
        //this instruction important to execute the Query and contain the result(without it the code that was written don't execute)
        if(rs.next())
        {
       if(rs.getBoolean("status")==true) System.out.println("The task is completed");
       else System.out.println("The task is not completed");
        }
        else System.out.println("The name is not found in the data");
        rs.close();
        st.close();
        con.close();
    }
    public void ReadTask(String u)throws Exception{  //Read(Display) all tasks to one user
    Connection con=DriverManager.getConnection(url,user,password);
    PreparedStatement st=con.prepareStatement("select * from tasks where username=?");
        st.setString(1, u);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) System.out.println("The name is not found in the data");
        else {
            do {
                System.out.println("ID:"+rs.getInt(1));
               // System.out.println("username:"+rs.getString(2));
                System.out.println("Task:"+rs.getString(3));
                System.out.println("Description:"+rs.getString(4));
              boolean status = rs.getBoolean("status");
                if (status == true) System.out.println("The task is completed");
                else System.out.println("The task is not completed");

            } while (rs.next());
        }
        rs.close();
        st.close();
        con.close();
    }
    public void UpdateTask(String u,String old_task,String new_task,String new_description)throws Exception{  //updata
    Connection con=DriverManager.getConnection(url,user,password);
    PreparedStatement st=con.prepareStatement("update tasks set task=?,description=?,status=? where username=? and task=?");
    st.setString(1,new_task);
    st.setString(2,new_description);
    st.setBoolean(3,true);  //needing review
    st.setString(4,u);
    st.setString(5,old_task);
    st.executeUpdate(); //Must write to execute the code
    st.close();
    con.close();
    }
    public void DeleteTask(String u,String t)throws Exception  //delete
    {
    Connection con=DriverManager.getConnection(url,user,password);
    PreparedStatement st=con.prepareStatement("delete from tasks where username=? and task=?");
    st.setString(1,u);
    st.setString(2,t);
    st.executeUpdate(); //Must write to execute the update
    }
}