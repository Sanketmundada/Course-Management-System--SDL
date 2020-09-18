import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class Message implements Serializable {
    public String msg;
}

public class ClientHandler extends Thread {
    private Socket client;
    private ArrayList<ClientHandler> clients;
    static PrintWriter toclient;
    static BufferedReader fromClient;

    static ObjectOutputStream dout;
    static ObjectInputStream din;
    static Scanner s;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        toclient = new PrintWriter(client.getOutputStream(), true);

        din = new ObjectInputStream(client.getInputStream());
        dout = new ObjectOutputStream(client.getOutputStream());

    }

    @Override
    public void run() {
        try {

            System.out.println(Thread.currentThread().getName());
            String welcomeString = "********** WELCOME TO THE E-ERA **********";
            String choice = "1. Learner  2. Instructor  3. Admin  4. Chat  5. Exit ";

            toclient.println(welcomeString);
            int option;

            do {
                toclient.println(choice);

                /* for options */
                option = Integer.parseInt(fromClient.readLine());
                System.out.println("[SERVER]: Selected Option : " + option);

                switch (option) {
                    case 1:
                        User user;
                        int choice1, choice2;

                        do {
                            choice1 = Integer.parseInt(fromClient.readLine());
                            System.out.println("[SERVER]: Selected Option : " + choice1);
                            switch (choice1) {

                                case 1:
                                    Main obj1 = (Main) din.readObject();
                                    boolean isRegistered = obj1.check_signup();
                                    System.out.println("[FROM CLIENT] : " + obj1.username);
                                    System.out.println("[SERVER] : Valid Check -> " + isRegistered);
                                    if (isRegistered) {
                                        String successMsg = "[FROM SERVER] : User Registered Successfully !!!";
                                        toclient.println(successMsg);
                                    } else {
                                        String errorMsg = "[FROM SERVER] : Username Already Exists...";
                                        toclient.println(errorMsg);
                                    }
                                    break;
                                case 2:
                                    UserLogin userLogin = (UserLogin) din.readObject();
                                    Main obj = new Main();
                                    System.out.println("[FROM CLIENT] : " + userLogin.userName);
                                    System.out.println("[FROM CLIENT] : " + userLogin.password);
                                    boolean isValid = userLogin.tryLogin();
                                    System.out.println("[SERVER] : Valid Check -> " + isValid);
                                    if (isValid) {
                                        user = userLogin.getUser();
                                        String msgSuccess = "[FROM SERVER] : Welcome, " + user.getFirstName();
                                        toclient.println(msgSuccess);
                                        do {
                                            choice2 = Integer.parseInt(fromClient.readLine());
                                            switch (choice2) {
                                                case 1:
                                                    System.out.println("[SERVER]: Course List");
                                                    Main newMain = new Main();
                                                    CourseList obj3 = new CourseList();
                                                    dout.writeObject(obj3);
                                                    break;
                                                case 2:
                                                    System.out.println("[SERVER]: Profile");
                                                    dout.writeObject(user);
                                                    break;
                                                case 3:
                                                    System.out.println("[SERVER]: MyCourses");
                                                    break;
                                                case 4:
                                                    System.out.println("[SERVER] : Buy a Course");
                                                    break;
                                                default:
                                                    break;
                                            }

                                        } while (choice2 != 5);
                                    } else {
                                        String msgError = "InValid Credentials.";
                                        toclient.println(msgError);
                                    }

                                    break;
                                default:
                                    break;
                            }
                        } while (choice1 != 3);
                        break;
                    case 2:
                        Instructor instructor;
                        int choice3, choice4;
                        do {
                            choice3 = Integer.parseInt(fromClient.readLine());
                            System.out.println("[SERVER]: Selected Option : " + choice3);
                            switch (choice3) {
                                case 1:
                                    Main obj1 = (Main) din.readObject();
                                    boolean isRegistered = obj1.check_signup_Instructor();
                                    System.out.println("[FROM CLIENT] : " + obj1.username);
                                    System.out.println("[SERVER] : Valid Check -> " + isRegistered);
                                    if (isRegistered) {
                                        String successMsg = "[FROM SERVER] : User Registered Successfully !!!";
                                        toclient.println(successMsg);
                                    } else {
                                        String errorMsg = "[FROM SERVER] : Username Already Exists...";
                                        toclient.println(errorMsg);
                                    }
                                    break;
                                default:
                                    break;

                            }
                        } while (choice3 != 3);
                        break;
                    case 3:
                        int choice5, choice6;
                        do {
                            choice5 = Integer.parseInt(fromClient.readLine());
                            System.out.println("[SERVER]: Selected Option : " + choice5);
                            switch (choice5) {
                                case 1:
                                    break;
                                case 2:
                                    Main obj8 = new Main();
                                    AdminLogin adminLogin = (AdminLogin) din.readObject();
                                    Admin admin;
                                    boolean valid = adminLogin.tryLogin();
                                    System.out.println("[SERVER] status -> " + valid);
                                    if (valid) {
                                        toclient.println(1);
                                        admin = adminLogin.getAdmin();
                                        toclient.println("[FROM SERVER] Welcome, " + admin.getFirstName());
                                    } else {
                                        toclient.println(0);
                                        toclient.println("Error in Login!");
                                    }

                                    break;
                                default:
                                    break;
                            }
                        } while (choice5 != 3);
                        break;
                    case 4:
                        /* CHAT APPLICATION */
                        Scanner scan = new Scanner(System.in);
                        System.out.println("[SERVER] : Welcome to the Chat Portal");
                        System.out.println("Chat With Client (Use 'BYE' to close)");
                        while (true) {
                            Message message = new Message();

                            Message clientMessage = (Message) din.readObject();
                            System.out.println("[Client] Message : " + clientMessage.msg);
                            if (clientMessage.msg.toUpperCase().equals("BYE")) {
                                System.out.println("Client has closed connection ...");
                                break;
                            }
                            System.out.print("[Server] Your Message : ");
                            message.msg = scan.nextLine();
                            System.out.println("Waiting for client response..");
                            dout.writeObject(message);
                            if (message.msg.toUpperCase().equals("BYE")) {
                                System.out.println("You have closed connection ...");
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }

            } while (option != 5);
            client.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
