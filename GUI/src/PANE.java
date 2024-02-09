import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import main.java.jfxDemo.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class PANE implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private TextArea b2;
    @FXML
    private TextArea b3;

    private Client client;

    public void sendMessage(ActionEvent actionEvent) {
        String text = b2.getText();
        if (text == null){
            return;
        }
        b2.setText("");

        client.sendAll(text);
    }

    public void receiveMessage(String msg) {
        b3.setText(msg);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b3.setEditable(false);
        b3.setWrapText(true);

        client = new Client("127.0.0.1", 8888);
    }
}