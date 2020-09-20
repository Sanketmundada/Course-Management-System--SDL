import java.util.*;

public class Course {
    private int courseId;
    private String CourseName;
    private int instructorId;
    private int noOfLearners;

    public Course(int courseId, String CourseName, int instructorId) {
        this.courseId = courseId;
        this.CourseName = CourseName;
        this.instructorId = instructorId;
        this.noOfLearners = 0;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public int getnoOfLearners() {
        return noOfLearners;
    }

}