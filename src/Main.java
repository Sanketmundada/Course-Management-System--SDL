import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.sql.*;

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
    private int InstructorId;
    private int noOfLearners;

    public Main() {

        allAdmins.put(adminIdCount, new Admin(adminIdCount++, "admin", "sanket", "mundada", "s@gmail.com", "qwerty"));
        allUsers.put(userCount, new User(userCount++, "sanmun", "sanket", "mundada", "a@gmail.com", "123456"));
        allInstructors.put(instructorIdCount,
                new Instructor(instructorIdCount++, "instructor1", "brad", "traversy", "b@gmail.com", "brad"));
//        allCourses.put(1, new Course(1, "Java", "Navin Reddy"));
//        allCourses.put(2, new Course(2, "React JS", "Brad Traversy"));
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

    public Boolean check_signup() throws Exception {
        Boolean valid = false;
        // Register for JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");

        Statement stmt = con.createStatement();

        ResultSet st = stmt.executeQuery("select * from user where user_name = " + "'" + username + "';");
        while(st.next()) 
        	valid=true;

        if (valid)
            return false;
        allUsers.put(userCount, new User(userCount++, username, firstName, lastName, mailId, password));
        stmt.executeUpdate("INSERT INTO user VALUES ( DEFAULT," + "'" + firstName + "'," + "'" + lastName + "'," + "'" + mailId + "'," +"'" + username + "'," + "'" + password + "');");
        return true;
        
    
       
        
    }

    public Boolean check_signup_Instructor() throws Exception {
        Boolean valid = false;
     // Register for JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");

        Statement stmt = con.createStatement();
        ResultSet st = stmt.executeQuery("select * from instructor where user_name = " + "'" + username + "';");
        while(st.next()) 
        	valid=true;
        if (valid)
            return false;
        allInstructors.put(instructorIdCount,
                new Instructor(instructorIdCount++, username, firstName, lastName, mailId, password));
        stmt.executeUpdate("INSERT INTO instructor VALUES ( DEFAULT," + "'" + firstName + "'," + "'" + lastName + "'," + "'" + mailId + "'," +"'" + username + "'," + "'" + password + "');");
        return true;
    }

    public void add_course() throws Exception {
    	// Register for JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");

        Statement stmt = con.createStatement();
  
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Details of the Course to be Added : \n");
        System.out.println("Enter Course-Name:  ");
        CourseName = scan.nextLine();
        System.out.println();
        System.out.println("Enter the Instructor-Id:  ");
        InstructorId = scan.nextInt();
        System.out.println();
        //Adding Course To Database
        stmt.executeUpdate("INSERT INTO course VALUES ( DEFAULT," + "'" + CourseName + "',"  + InstructorId + "," + noOfLearners+ ");");
        
        ResultSet st = stmt.executeQuery("select course_id from course where course_name = " + "'" + CourseName + "';");
        while(st.next())   
        System.out.println("Course Added SuccessFully !\n Id assigned : " + st.getInt(1));
        scan.close();
    }

    void delete_course() throws Exception {
        Scanner scan = new Scanner(System.in);
        int no;
        int choice;
        System.out.println("Enter the Id of the Course to be deleted: \n");
        no = scan.nextInt();
        System.out.println("Are you Sure that you want to delete the Course ?(1/0)");
        choice = scan.nextInt();

        if (choice == 1) {
//            allCourses.remove(no);
         // Register for JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                    "scm@2000");

            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("DELETE FROM course where course_id =" + no + ";" );
            
            System.out.println("Course with the Id: " + no + "deleted Successfully.");
        }
        scan.close();
    }
    
    void buy_course(int c_id,int u_id) throws Exception {
    	
    	   Class.forName("com.mysql.cj.jdbc.Driver");
           // Open a connection
           Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                   "scm@2000");

           Statement stmt = con.createStatement();
           
           stmt.executeUpdate("INSERT INTO coursestudents VALUES( DEFAULT, " + c_id +"," + u_id +");");
           stmt.executeUpdate("UPDATE course SET no_of_learner=no_of_learner+1 where course_id = " + c_id + ";");
     }
    
    void my_courses(int u_id) throws Exception{
    	Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");

        Statement stmt = con.createStatement();
        
       ResultSet st= stmt.executeQuery("select course_id,course_name from coursestudents natural join course where user_id = " + u_id + ";");
        while(st.next()) 
        {
        	System.out.println(st.getInt(1)+ ".  " + st.getString(2));
        }
    }
    
    
}