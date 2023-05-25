package ichat.ichat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class _AppForm extends Application {

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(_AppForm.class.getResource("Form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("iChat User Mode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){launch();}

}
