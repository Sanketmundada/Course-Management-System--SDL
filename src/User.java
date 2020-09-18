import java.io.Serializable;
import java.util.Vector;

public class User implements Serializable {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String mailId;
    private String password;

    private Vector<Integer> myCourses = new Vector<Integer>();

    public User(int id, String userName, String firstName, String lastName, String mailId, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailId = mailId;
        this.password = password;
        this.userName = userName;
        this.myCourses.clear();
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    // public void addCourse(int id) {
    // myCourses.add(id);
    // System.out.println(allCourses.get(id));
    // }

}