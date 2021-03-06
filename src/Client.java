import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.*;
import java.io.*;

public class Client {
    static Socket soc;
    static BufferedReader bf;
    static PrintWriter toserver;
    static Scanner s;

    static ObjectOutputStream dout;
    static ObjectInputStream din;

    static {
        try {
            s = new Scanner(System.in);
            soc = new Socket("127.0.0.1", 8000);
            bf = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            toserver = new PrintWriter(soc.getOutputStream(), true);

            dout = new ObjectOutputStream(soc.getOutputStream());
            din = new ObjectInputStream(soc.getInputStream());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException {

        System.out.println("Client is communicating from PORT " + soc.getLocalPort());
        String welcomeMsg = bf.readLine();
        System.out.println(welcomeMsg);
        String choiceMsg = bf.readLine();
        int option;
        do {
            System.out.println(choiceMsg);

            option = s.nextInt();
            toserver.println(option);

            switch (option) {
                case 1:
                    System.out.println(" ********** Welcome To Learner Section **********\n\n");
                    int choice1;
                    do {
                        System.out.println("1. Sign-Up \n2. Login \n3. Exit");
                        choice1 = s.nextInt();

                        toserver.println(choice1);

                        switch (choice1) {

                            /* SIGNUP */
                            case 1:
                                Main obj = new Main();
                                obj.sign_up();
                                dout.writeObject(obj);
                                String registerResponse = bf.readLine();
                                System.out.println(registerResponse);
                                break;
                            /* LOGIN */
                            case 2:
                                UserLogin userLogin = new UserLogin();
                                userLogin.getLoginCredentials();
                                dout.writeObject(userLogin);

                                String response = bf.readLine();
                                System.out.println("Waiting for response from server...");
                                System.out.println(response);
                                if (response != "InValid Credentials.") {
                                    /* Perform Req. Actions further */
                                    int choice2;
                                    do {
                                        System.out.println(
                                                "1. Course-List\n2. Profile\n3. My-Courses\n4. Buy a Course\n5.Exit");
                                        choice2 = s.nextInt();
                                        toserver.println(choice2);
                                        User user;
                                        switch (choice2) {
                                            case 1:
                                                CourseList CourseListObj = (CourseList) din.readObject();
                                                try {
                                                    CourseListObj.DisplayCourses();
                                                    }catch(Exception e) {
                                                    	System.out.println(e.getMessage());
                                                    }
                                                System.out.println("___________________________");
                                                break;
                                            case 2:
                                                user = (User) din.readObject();
                                                System.out.println(user.getFirstName());
                                                System.out.println(user.getLastName());
                                                System.out.println(user.getUserName());
                                                System.out.println("____________________________");
                                                break;
                                            case 3:
                                            	 user = (User) din.readObject();
                                            	 System.out.println("**********My Courses*************");
                                            	 Main newobj2=new Main();
                                            	 int us_id=user.getId();
                                            	 try {
                                            	 newobj2.my_courses(us_id);
                                            	 }catch( Exception e ) {
                                            		 System.out.println(e.getMessage());
                                            	 }
                                            	 break;
                                            case 4:
                                            	 user = (User) din.readObject();
                                            	 System.out.println("Enter the course-Id of the course to be purchased : ");
                                            	 int id=s.nextInt();
                                            	 int u_id=user.getId();
                                            	 Main newobj1=new Main();
                                            	 try {
                                            	 newobj1.buy_course(id,u_id);
                                            	 }catch( Exception e ) {
                                            		 System.out.println(e.getMessage());
                                            	 }
                                            	 break;
                                            default:
                                                break;
                                        }

                                    } while (choice2 != 5);
                                }
                                break;
                            case 3:
                                choice1 = 4;
                                break;
                            default:
                                break;
                        }
                    } while (choice1 != 4);

                    break;
                case 2:
                    System.out.println(" ********** Welcome To Instructor Section **********\n\n");

                    int choiceI;
                    do {
                        System.out.println("1. Sign-Up \n2. Login \n3. Exit");
                        choiceI = s.nextInt();

                        toserver.println(choiceI);

                        switch (choiceI) {

                            /* SIGNUP */
                            case 1:
                                Main obj = new Main();
                                obj.sign_up();
                                dout.writeObject(obj);
                                String registerResponse = bf.readLine();
                                System.out.println(registerResponse);
                                break;
                            /* LOGIN */
                            case 2:
                                UserLogin userLogin = new UserLogin();
                                userLogin.getLoginCredentials();
                                dout.writeObject(userLogin);

                                String response = bf.readLine();
                                System.out.println("Waiting for response from server...");
                                System.out.println(response);
                                if (response != "InValid Credentials.") {
                                    /* Perform Req. Actions further */
                                    int choice2;
                                    do {
                                        System.out.println(
                                                "1. Course-List\n2. Profile\n5.Exit");
                                        choice2 = s.nextInt();
                                        toserver.println(choice2);

                                        switch (choice2) {
                                            case 1:
                                                CourseList CourseListObj = (CourseList) din.readObject();
                                                try {
                                                CourseListObj.DisplayCourses();
                                                }catch(Exception e) {
                                                	System.out.println(e.getMessage());
                                                }
                                                break;
                                            default:
                                                break;
                                        }

                                    } while (choice2 != 5);
                                }
                                break;
                            case 3:
                                choiceI = 4;

                                break;
                            default:
                                break;
                        }
                    } while (choiceI != 4);
                    break;
                case 3:
                    /* ADMIN */
                    System.out.println(" ********** Welcome To Admin Section **********\n\n");
                    int choicea;

                    do {

                        System.out.println("\n1. Login \n3. Exit");
                        choicea = s.nextInt();

                        toserver.println(choicea);

                        switch (choicea) {
                            case 1:
                                AdminLogin adminLogin = new AdminLogin();
                                Admin admin;
                                adminLogin.getLoginCredentials();

                                dout.writeObject(adminLogin);
                                String status = bf.readLine();
                                String response = bf.readLine();

                                if (status == "1") {
                                    System.out.println(response);

                                } else {
                                    System.out.println(response);
                                    int choicen;
                                    Main objnew = new Main();
                                    do {

                                        System.out.println(
                                                "1. Add Course \n2. Delete Course \n3.Display Profile \n4. Exit");
                                        choicen = s.nextInt();
                                        toserver.println(choicen);
                                        switch (choicen){
                                            case 1:
                                                try {
                                                objnew.add_course();
                                                }catch(Exception e) {
                                                	System.out.println(e.getMessage());
                                                }
                                                break;
                                            case 2:
                                            	 try {
                                                     objnew.delete_course();
                                            	 }catch(Exception e) {
                                                     	System.out.println(e.getMessage());
                                                 }
                                            	 break;
                                            default:
                                                break;
                                        }

                                    } while (choicen != 4);

                                }

                                break;
                            default:
                                break;
                        }
                    } while (choicea != 3);
                    break;

                case 4:
                    /* CHAT APPLICATION */
                    Scanner scan = new Scanner(System.in);
                    System.out.println("[CLIENT] : Welcome to the Chat Portal");
                    System.out.println("Chat With Server (Use 'BYE' to close)");
                    while (true) {
                        Message message = new Message();
                        System.out.print("[CLIENT] Your Message :");
                        message.msg = scan.nextLine();

                        dout.writeObject(message);
                        if (message.msg.toUpperCase().equals("BYE")) {
                            System.out.println("You have closed connection ...");
                            break;
                        }
                        System.out.println("Waiting for server response..");
                        Message serverMessage = (Message) din.readObject();
                        System.out.println("[FROM SERVER] Message : " + serverMessage.msg);
                        if (serverMessage.msg.toUpperCase().equals("BYE")) {
                            System.out.println("Server have closed connection ...");
                            break;
                        }

                    }
                    break;
                default:
                    break;

            }

        } while (option != 5);

        bf.close();
        soc.close();

    }

}
