
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.*;

public class AdminLogin extends Main implements Serializable {

    private String userName;
    private String password;

    Admin currentAdmin;

    public AdminLogin() {

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

        ResultSet st = stmt.executeQuery("select * from admin where user_name = " + "'" + userName + "' AND password = " + "'" + password +"';");
        while(st.next()) 
        	{
        		valid=true;
        		Admin user = new Admin(st.getInt(1),st.getString(5),st.getString(1),st.getString(2),st.getString(3),st.getString(4));
        		currentAdmin = user;
        	}
//        Vector<Admin> admins = new Vector<Admin>(allAdmins.values());
//        for (int i = 0; i < admins.size() && !valid; i++) {
//            Admin admin = admins.get(i);
//            valid = (admin.getUserName().equals(userName) && admin.getPassword().equals(password));
//            if (valid) {
//                currentAdmin = admin;
//            }
//        }
        return valid;
    }

    public Admin getAdmin() {
        return currentAdmin;
    }
}