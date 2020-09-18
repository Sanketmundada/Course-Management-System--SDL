import java.io.Serializable;
import java.util.*;

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

    public boolean tryLogin() {
        boolean valid = false;

        Vector<User> users = new Vector<User>(allUsers.values());
        for (int i = 0; i < users.size() && !valid; i++) {
            User user = users.get(i);
            valid = (user.getUserName().equals(userName) && user.getPassword().equals(password));
            if (valid) {
                currentUser = user;
            }
        }
        return valid;
    }

    public User getUser() {
        return currentUser;
    }

}