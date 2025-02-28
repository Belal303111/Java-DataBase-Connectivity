import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.*;
import javax.swing.*;
public class Main {
    /**
     * The basic things in JDBC
     * 1)import the Connection , DriverManger libraries form sql after make sql as a library
     * ctr+alt+shift+s
     * 2)Connect to the database(you mast handel the Exceptions with it-->give throw)
     * 3)Statements and Execution
     * 4)Result
     * 5)close
     */
    public static void main(String[] args)throws Exception {
//        Class.forName("com.mysql.cj.jdbc.Driver");  //in the old version not here
        JFrame frame=new JFrame("My frame with JDBC");
        frame.setVisible(true);
        frame.setSize(400,500);
        frame.getContentPane().setBackground(Color.yellow);
        JLabel label=new JLabel();
        frame.add(label);
        label.setBounds(100,100,100,100);
        JButton button = new JButton("press here to display employee data");
        frame.add(button);
        button.setBounds(400,100,50,50);
        JLabel result =new JLabel();
        result.setBounds(50,50,250,250);
        frame.add(result);
        JTextArea area=new JTextArea(20,50);
        frame.add(area);
        area.setEditable(false);
        frame.add(new JScrollPane(area));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String url = "jdbc:postgresql://localhost:5432/MyDataBase";
                        String username = "postgres";
                        String password = "1234";
                        Connection con = DriverManager.getConnection(url, username, password);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("select fname,lname,minit,ssn from Employee");
                        area.setText("");
                        while (rs.next()) {
                            String row=(rs.getString("fname") + " " +
                                    rs.getString("lname") + " " +
                                    rs.getString("minit") + " " +
                                    rs.getString("ssn")+"\n");
                            area.append(row);
                        }
                        st.close();
                        rs.close();
                        con.close();
                    } catch (Exception x) {
                        System.out.println(x.getMessage());
                    }
                }
        });
        }
    }
    //the idea of todo list
    //need input to take the fname , lname from the user and give it its data
  //ex: USER: enterd fname=f,lname=l
//code:select user from table where fname=f and lname=l;
