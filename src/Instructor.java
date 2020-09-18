import java.util.Vector;

public class Instructor {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String mailId;
    private String password;

    public Instructor(int id, String userName, String firstName, String lastName, String mailId, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailId = mailId;
        this.password = password;
        this.userName = userName;
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

}