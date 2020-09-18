
import java.io.Serializable;
import java.util.*;

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

    public boolean tryLogin() {
        boolean valid = false;

        Vector<Admin> admins = new Vector<Admin>(allAdmins.values());
        for (int i = 0; i < admins.size() && !valid; i++) {
            Admin admin = admins.get(i);
            valid = (admin.getUserName().equals(userName) && admin.getPassword().equals(password));
            if (valid) {
                currentAdmin = admin;
            }
        }
        return valid;
    }

    public Admin getAdmin() {
        return currentAdmin;
    }

    public void addNewCourse() {

    }

}