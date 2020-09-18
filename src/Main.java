import java.io.Serializable;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Main implements Serializable {

    protected static Hashtable<Integer, Admin> allAdmins = new Hashtable<Integer, Admin>();
    protected static Hashtable<Integer, User> allUsers = new Hashtable<Integer, User>();
    protected static Hashtable<Integer, Course> allCourses = new Hashtable<Integer, Course>();
    protected static Hashtable<Integer, Instructor> allInstructors = new Hashtable<Integer, Instructor>();
    protected static int adminIdCount = 0;
    protected static int userCount = 0;
    protected static int instructorIdCount = 0;
    protected static int courseIdCount = 0;

    public String username;
    public String firstName;
    public String lastName;
    public String mailId;
    public String password;

    private String CourseName;
    private String InstructorName;
    private int noOfLearners;

    public Main() {
        allAdmins.put(adminIdCount, new Admin(adminIdCount++, "admin", "sanket", "mundada", "s@gmail.com", "qwerty"));
        allUsers.put(userCount, new User(userCount++, "sanmun", "sanket", "mundada", "a@gmail.com", "123456"));
        allInstructors.put(instructorIdCount,
                new Instructor(instructorIdCount++, "instructor1", "brad", "traversy", "b@gmail.com", "brad"));
        allCourses.put(1, new Course(1, "Java", "Navin Reddy"));
        allCourses.put(2, new Course(2, "React JS", "Brad Traversy"));
    }

    public void sign_up() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Your Details : \n");
        System.out.println("Enter your Username:  ");
        username = scan.nextLine();
        System.out.println();
        System.out.println("Enter your First-Name:  ");
        firstName = scan.nextLine();
        System.out.println();
        System.out.println("Enter your Last-Name:  ");
        lastName = scan.nextLine();
        System.out.println();
        System.out.println("Enter your mail-Id:  ");
        mailId = scan.nextLine();
        System.out.println();
        System.out.println("Set your password:  ");
        password = scan.nextLine();
        System.out.println();

    }

    public Boolean check_signup() {
        Boolean valid = false;

        Vector<User> users = new Vector<User>(allUsers.values());
        System.out.println(users);
        for (int i = 0; i < users.size() && !valid; i++) {
            User user = users.get(i);
            valid = (user.getUserName().equals(username));
        }

        if (valid)
            return false;
        allUsers.put(userCount, new User(userCount++, username, firstName, lastName, mailId, password));
        return true;
    }

    public Boolean check_signup_Instructor() {
        Boolean valid = false;

        Vector<Instructor> instructors = new Vector<Instructor>(allInstructors.values());
        System.out.println(instructors);
        for (int i = 0; i < instructors.size() && !valid; i++) {
            Instructor instructor = instructors.get(i);
            valid = (instructor.getUserName().equals(username));
        }

        if (valid)
            return false;
        allInstructors.put(instructorIdCount,
                new Instructor(instructorIdCount++, username, firstName, lastName, mailId, password));
        return true;
    }

    public void add_course() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Details of the Course to be Added : \n");
        System.out.println("Enter Course-Name:  ");
        CourseName = scan.nextLine();
        System.out.println();
        System.out.println("Enter the Instructor-Name:  ");
        InstructorName = scan.nextLine();
        System.out.println();
        allCourses.put(courseIdCount, new Course(courseIdCount++, CourseName, InstructorName));
        System.out.println("Course Added SuccessFully !\n Id assigned : " + courseIdCount);
    }

    void delete_course() {
        Scanner scan = new Scanner(System.in);
        int no;
        int choice;
        System.out.println("Enter the Id of the Course to be deleted: \n");
        no = scan.nextInt();
        System.out.println("Are you Sure that you want to delete the Course ?(1/0)");
        choice = scan.nextInt();

        if (choice == 1) {
            allCourses.remove(no);
            System.out.println("Course with the Id: " + no + "deleted Successfully.");
        }
        System.out.println("Hello");
        System.out.println("Hashtable : " + allCourses);
    }
}