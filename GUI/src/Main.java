import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    //  @Override
    public void start(Stage primaryStage) throws IOException {
        URL location = getClass().getResource("pane.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 600,400);
//     NewPaneController controller = fxmlLoader.getController();
//     controller.init();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Main method
    public static void main(String[] args) {
        Application.launch(args);
    }
}