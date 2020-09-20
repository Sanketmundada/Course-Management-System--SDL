import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CourseList extends Main {

    public CourseList() {

    }

    public void DisplayCourses() throws Exception {
        System.out.println("********** Courses **********\n");
//        Vector<Course> courses = new Vector<Course>(allCourses.values());
//        for (int i = 0; i < courses.size(); i++) {
//            System.out.println(
//                    (i + 1) + ". " + courses.get(i).getCourseName() + courses.get(i).getCourseIdInstructorName());
//        }
     // Register for JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Open a connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root",
                "scm@2000");
        Statement stmt = con.createStatement();

        ResultSet st = stmt.executeQuery("select course_id,course_name,first_name,last_name from course natural join instructor;");
        while(st.next()) 
        	{
        		System.out.println(st.getInt(1) + "." + "  " + st.getString(2) + " by  "+ st.getString(3) + " " + st.getString(4));
        	}
    }

}