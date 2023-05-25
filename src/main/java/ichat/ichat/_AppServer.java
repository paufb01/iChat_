package ichat.ichat;

import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class _AppServer extends Application {
    public Image image;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(_AppServer.class.getResource("AppServer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        stage.setTitle("iChat: Server");
        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(900);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

