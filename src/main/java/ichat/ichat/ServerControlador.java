package ichat.ichat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControlador {
    @FXML
        TextArea txtArea;
        @FXML
        Button btnConnectar, btnTancar;

        @FXML
        public void Connect(){
            ServerSocket serverSocket = null;
            Socket socol= null;
            DataInputStream entrada = null;
            DataOutputStream sortida = null;
            String usuari="";
            Server openServer = new Server(serverSocket,socol,entrada,sortida,usuari,txtArea);
            Thread filServer = new Thread(openServer);
            filServer.start();

        }

        public void Tancar(ActionEvent actionEvent) {
            System.exit(0);
            Platform.exit();

        }
    }

