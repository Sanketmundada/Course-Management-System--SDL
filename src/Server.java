import java.util.*;
import java.net.*;
import java.io.*;
/*New*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static Socket soc;
    static BufferedReader bf;
    static ServerSocket ss;
    protected static int userCount = 0;

    /* New */
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    static {
        try {
            ss = new ServerSocket(8000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) throws ClassNotFoundException, IOException {
        Integer i = 0;
        while (i < 4) {
            System.out.println("[SERVER]: waiting for Client...");

            System.out.println("Server has reserved port no : " + ss.getLocalPort() + " for this service.");

            // Confirming arrival of client
            soc = ss.accept();
            System.out.println("Client " + soc.getInetAddress() + " is communicating from PORT " + soc.getPort());
            // ClientHandler clientThread = new ClientHandler(soc, clients);

            // pool.execute(clientThread);
            // create a new thread object
            Thread t = new ClientHandler(soc, clients);

            // Invoking the start() method
            t.start();
            // clients.add(t);
            i++;
        }
        // soc.close();
        // ss.close();
    }
}
