package ichat.ichat;


import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GetClient implements Runnable{
    private DataInputStream entrada = null;
    TextArea txtArea;

    public GetClient(DataInputStream entrada, TextArea txtArea) {

        this.entrada = entrada;
        this.txtArea = txtArea;
    }
    @Override
    public void run() {
        while (true) {
            try {
                String missatge = entrada.readUTF();
                if (!missatge.equals("SERVIDOR: Fi")) {
                    txtArea.appendText("\n" + missatge);
                } else {

                    txtArea.appendText("El servidor ha finalitzat el xat.");
                    System.exit(0);
                }
            } catch (IOException ex) {
                Logger.getLogger(GetClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}