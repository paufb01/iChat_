package ichat.ichat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ManagementController {
    @FXML
    TextField txtData;
    @FXML
    Button btnCerca;
    @FXML
    Label lblMissatges;

    public void GetMessages(){
        String data = txtData.getText();
        MongoDB mongo = new MongoDB();
         mongo.Find(data, lblMissatges);
    }



}


