import java.io.Serializable;

public class Admin implements Serializable {
    private int id;
    private String userName, fName, lName;
    private String password;
    private String email;

    public Admin(int id, String userName, String fName, String lName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public int getUserId() {
        return id;
    }

}