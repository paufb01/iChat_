package ichat.ichat;

import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetServer implements Runnable {

    Socket socol;
    private DataInputStream entrada;
    private DataOutputStream sortida;
    private String usuari;
    private TextArea txtArea;
    public GetServer(Socket socol, DataInputStream entrada, DataOutputStream sortida, String usuari, TextArea txtArea) {
        this.socol = socol;
        this.entrada = entrada;
        this.sortida = sortida;
        this.usuari = usuari;
        this.txtArea = txtArea;
    }

    public Socket getSocol() {
        return socol;
    }

    public void setSocol(Socket socol) {
        this.socol = socol;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSortida() {
        return sortida;
    }

    public void setSortida(DataOutputStream sortida) {
        this.sortida = sortida;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String missatge = entrada.readUTF();
                if (!missatge.equals(usuari + ": Fi")){
                    txtArea.appendText("\n"+missatge);
                    for(GetServer llista: Server.Clients){
                        if(!llista.getUsuari().equals(usuari)){
                            llista.getSortida().writeUTF(missatge);
                            llista.getSortida().flush();
                        }
                    }
                } else{
                    for(GetServer llista: Server.Clients){
                        if(!llista.getUsuari().equals(usuari)){
                            llista.getSortida().writeUTF("\n"+usuari + " Ha deixat el xat.");
                            llista.getSortida().flush();

                        }
                    }
                    txtArea.appendText("\n"+usuari + " Ha deixat el xat.");
                    for(int i=0;i<Server.Clients.size();i++){
                        if(Server.Clients.get(i).getUsuari().equals(usuari)){
                            Server.Clients.remove(i);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
