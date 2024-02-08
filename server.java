import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
    private static final int PORT = 12345; //12345 for testing 
    private static List<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {//attempt to connect
            System.out.println("Server is running");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("A user has connected: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                clients.add(clientHandler);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
