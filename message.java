import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {

    private PrintWriter out;
    private BufferedReader in;
    private TextArea messageArea;
    private TextField textField;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        messageArea = new TextArea();
        textField = new TextField();
        Button sendButton = new Button("Send");

        sendButton.setOnAction(event -> {
            String message = textField.getText();
            sendMessage(message);
            textField.clear();
        });

        root.getChildren().addAll(messageArea, textField, sendButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.show();

        // Connect to the server
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread listenerThread = new Thread(this::listenForMessages);
            listenerThread.setDaemon(true);
            listenerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        out.println(message);
    }

    private void listenForMessages() {
        try {
            while (true) {
                String receivedMessage = in.readLine();
                if (receivedMessage != null) {
                    messageArea.appendText("Server: " + receivedMessage + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
