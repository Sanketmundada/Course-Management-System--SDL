import java.io.Serializable;
import java.util.Vector;

public class CourseList extends Main {

    public CourseList() {

    }

    public void DisplayCourses() {
        System.out.println("********** Courses **********\n");
        Vector<Course> courses = new Vector<Course>(allCourses.values());
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + courses.get(i).getCourseName() + courses.get(i).getCourseIdInstructorName());
        }
    }

}