import java.io.Serializable;
import java.util.*;

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

    public boolean tryLogin() {
        boolean valid = false;

        Vector<Instructor> users = new Vector<Instructor>(allInstructors.values());
        for (int i = 0; i < users.size() && !valid; i++) {
            Instructor user = users.get(i);
            valid = (user.getUserName().equals(userName) && user.getPassword().equals(password));
            if (valid) {
                currentInstructor = user;
            }
        }
        return valid;
    }

    public Instructor getUser() {
        return currentInstructor;
    }

}