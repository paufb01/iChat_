package ichat.ichat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;


public class ClientControlador<CreateXml> {
    @FXML
    TextField txtUsuari, txtMissatge, txtCognoms;

    @FXML
    TextArea txtArea;
    Clients client1;
    Clients client = new Clients();


    @FXML
    public void sendMessage() {
        MongoDB mongo = new MongoDB();
        String missatge = txtMissatge.getText();

        SendClient sc = new SendClient(client1.getSortida(), client1.getUsuari(), missatge, txtArea);
        String usuariXat = client1.getUsuari();
        Thread enviar = new Thread(sc);
        enviar.start();
        mongo.InsertToMongo(usuariXat, missatge);
        txtMissatge.clear();
    }

    public void UsersValidation(ActionEvent actionEvent) {
        String nom = txtUsuari.getText();
        String cognom = txtCognoms.getText();
        Alert dialeg = new Alert(Alert.AlertType.INFORMATION);
        try {
            String usuari = nom + " " + cognom;
            client1 = client.connectarClients(usuari, txtArea);
            dialeg = new Alert(Alert.AlertType.INFORMATION);
            dialeg.setTitle("Informació");
            dialeg.setHeaderText("SUCCESS");
            dialeg.setContentText("Benvingut/da, " + usuari);
            dialeg.initStyle(StageStyle.UTILITY);
            dialeg.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            dialeg = new Alert(Alert.AlertType.ERROR);
            dialeg.setTitle("ERROR");
            dialeg.setHeaderText("ERROR: No s'ha pogut connectar a al servidor.");
            dialeg.setContentText(" Usuari incorrecte o potser el servidor no espera cap client?");
            dialeg.initStyle(StageStyle.UTILITY);
            dialeg.showAndWait();
            txtUsuari.clear();
            txtCognoms.clear();
        }
    }
}

