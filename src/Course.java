import java.util.*;

public class Course {
    private int courseId;
    private String CourseName;
    private String InstructorName;
    // private Calendar dateOfPublish;
    private int noOfLearners;

    public Course(int courseId, String CourseName, String InstructorName) {
        this.courseId = courseId;
        this.CourseName = CourseName;
        this.InstructorName = InstructorName;
        // this.dateOfPublish = dateOfPublish;
        this.noOfLearners = 0;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    // public Calendar getdateOfPublish() {
    // return dateOfPublish;
    // }

    public String getCourseIdInstructorName() {
        return InstructorName;
    }

    public int getnoOfLearners() {
        return noOfLearners;
    }

}