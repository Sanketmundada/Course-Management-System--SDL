import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.*;

public class InstructorLogin extends Main implements Serializable {
    public String userName;
    public String password;

    Instructor currentInstructor;

    public InstructorLogin() {
    }

    public void getLoginCredentials() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Username : ");
        userName = in.nextLine();
        System.out.print("Enter Password : ");
        password = in.nextLine();
    }

    public boolean tryLogin() throws Exception {
        boolean valid = false;
     // Register for JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");
        Statement stmt = con.createStatement();

        ResultSet st = stmt.executeQuery("select * from instructor where user_name = " + "'" + userName + "' AND password = " + "'" + password +"';");
        while(st.next()) 
        	{
        		valid=true;
        		Instructor user = new Instructor(st.getInt(1),st.getString(5),st.getString(1),st.getString(2),st.getString(3),st.getString(4));
        		currentInstructor = user;
        	}
//        Vector<Instructor> users = new Vector<Instructor>(allInstructors.values());
//        for (int i = 0; i < users.size() && !valid; i++) {
//            Instructor user = users.get(i);
//            valid = (user.getUserName().equals(userName) && user.getPassword().equals(password));
//            if (valid) {
//                currentInstructor = user;
//            }
//        }
        return valid;
    }

    public Instructor getUser() {
        return currentInstructor;
    }

}