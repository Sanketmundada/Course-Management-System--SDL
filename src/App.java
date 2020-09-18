import java.io.*;
import java.util.*;

public class App {

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        Main obj = new Main();
        int option, choice1, choice2, choice3;
        System.out.println("********** WELCOME TO THE E-ERA **********\n\n");

        System.out.println("1. Learner\n2. Instructor\n3. Admin");
        option = sc.nextInt();

        switch (option) {

            case 1:
                System.out.println(" ********** Welcome To Learner Section **********\n\n");
                UserLogin userLogin = new UserLogin();
                User user;
                Boolean ch = true;

                while (ch) {
                    System.out.println("1. Sign-Up \n2. Login \n3. Exit");
                    choice1 = sc.nextInt();
                    switch (choice1) {
                        case 1:
                            Boolean is_valid = obj.check_signup();
                            if (is_valid) {
                                System.out.println("You are Registered Successfully !!!");
                            } else {
                                System.out.println("User Already Exists !");
                            }
                            break;
                        case 2:
                            userLogin.getLoginCredentials();

                            boolean isValid = userLogin.tryLogin();

                            if (isValid) {
                                user = userLogin.getUser();
                                System.out.println("\nWelcome, " + user.getFirstName() + "\n");

                                /* Perform Req. Actions further */

                                System.out.println("1. Course-List\n2. Profile\n3. My-Courses\n4. Buy a Course");
                                choice2 = sc.nextInt();

                                switch (choice2) {
                                    case 1:
                                        CourseList courseList = new CourseList();
                                        courseList.DisplayCourses();
                                        break;
                                    case 4:
                                        int cNo;
                                        System.out.println("Enter the id of course which you want :");
                                        cNo = sc.nextInt();
                                        user.addCourse(cNo);
                                    default:
                                        break;
                                }

                            } else {
                                System.out.println("Invalid Credentials.");
                            }

                            break;
                        case 3:
                            ch = false;
                            break;
                        default:
                            break;
                    }

                }
                break;
            case 3:
                System.out.println(" ********** Welcome To Admin Section **********\n\n");
                AdminLogin adminLogin = new AdminLogin();
                Admin admin;
                adminLogin.getLoginCredentials();

                boolean valid = adminLogin.tryLogin();

                if (valid) {
                    admin = adminLogin.getAdmin();
                    System.out.println("\nWelcome, " + admin.getFirstName() + "\n");

                    Boolean sw = true;
                    while (sw) {

                        /* Perform Req. Actions further */
                        System.out.println("1. Add-Course\n2. Delete-Course\n3.Display Courses\n4.Exit");
                        choice3 = sc.nextInt();

                        switch (choice3) {
                            case 1:
                                obj.add_course();
                                break;
                            case 2:
                                obj.delete_course();
                                break;
                            case 3:
                                CourseList courseList = new CourseList();
                                courseList.DisplayCourses();
                                break;
                            case 4:
                                sw = false;
                            default:
                                break;
                        }

                    }

                } else {
                    System.out.println("Invalid Credentials.");
                }
                break;
            default:
                break;

        }

        System.out.println("Thank You for Visiting E-ERA !");

    }

}