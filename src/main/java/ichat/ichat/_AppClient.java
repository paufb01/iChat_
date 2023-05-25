package ichat.ichat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class _AppClient extends Application{
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(_AppClient.class.getResource("AppClient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("iChat User Mode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){launch();}

}
