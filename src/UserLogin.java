import java.io.Serializable;
import java.util.*;
import java.sql.*;

public class UserLogin extends Main implements Serializable {
    public String userName;
    public String password;

    User currentUser;

    public UserLogin() {
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

        ResultSet st = stmt.executeQuery("select * from user where user_name = " + "'" + userName + "' AND password = " + "'" + password +"';");
        while(st.next()) 
        	{
        		valid=true;
        		User user = new User(st.getInt(1),st.getString(5),st.getString(1),st.getString(2),st.getString(3),st.getString(4));
        		currentUser = user;
        	}
//        Vector<User> users = new Vector<User>(allUsers.values());
//        for (int i = 0; i < users.size() && !valid; i++) {
//            User user = users.get(i);
//            valid = (user.getUserName().equals(userName) && user.getPassword().equals(password));
//            if (valid) {
//                currentUser = user;
//            }
//        }
        System.out.println(valid);
        return valid;
    }

    public User getUser() {
        return currentUser;
    }

}