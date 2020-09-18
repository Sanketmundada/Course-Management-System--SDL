import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import java.util.*;

class Message implements Serializable {
    public String msg;
}

public class ServerConnection implements Runnable {
    private Socket server;

    public ServerConnection(Socket st) throws IOException {
        server = st;

    }

    @Override
    public void run() {
        try {
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

                                            switch (choice2) {
                                                case 1:
                                                    CourseList CourseListObj = (CourseList) din.readObject();
                                                    CourseListObj.DisplayCourses();
                                                    System.out.println("___________________________");
                                                    break;
                                                case 2:
                                                    User user = (User) din.readObject();
                                                    System.out.println(user.getFirstName());
                                                    System.out.println(user.getLastName());
                                                    System.out.println(user.getUserName());
                                                    System.out.println("____________________________");
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
                                                    "1. Course-List\n2. Profile\n3. My-Courses\n4. Buy a Course\n5.Exit");
                                            choice2 = s.nextInt();
                                            toserver.println(choice2);

                                            switch (choice2) {
                                                case 1:
                                                    CourseList CourseListObj = (CourseList) din.readObject();
                                                    CourseListObj.DisplayCourses();
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

                            System.out.println("1. Sign-Up \n2. Login \n3. Exit");
                            choicea = s.nextInt();

                            toserver.println(choicea);

                            switch (choicea) {
                                case 1:
                                    break;
                                case 2:
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

                                        do {

                                            System.out.println(
                                                    "1. Add Course \n2. Delete Course \n3.Display Profile \n4. Exit");
                                            choicen = s.nextInt();

                                            switch (choicen) {
                                                case 1:
                                                    Main objnew = new Main();
                                                    objnew.add_course();
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
