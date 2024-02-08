import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket, List<ClientHandler> clients) {
        this.clientSocket = clientSocket;
        this.clients = clients;
    }
    @Override
    public void run() {
        try {
            //reads data from specific client socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String message = reader.readLine();
                if (message == null || message.equalsIgnoreCase("quit")) {
                    break;
                }
                broadcastMessage(message);
            }
        } catch (IOException e) {
            System.err.println("Error handling client");
        } finally {
            closeSocket();
        }
    }
    private void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            if (client != this) {
                client.sendMessage(message);
            }
        }
    }
    private void sendMessage(String message) {
        out.println(message);
    }
    private void closeSocket() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing client socket");
        }
    }
}
