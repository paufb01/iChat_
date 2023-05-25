package ichat.ichat;

import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;


public class Server implements Runnable{

    ServerSocket serverSocket;
    Socket socol;
    DataInputStream entrada = null;
    DataOutputStream sortida = null;
    String usuari;
    TextArea txtArea;


    public static ArrayList<GetServer> Clients = new ArrayList<GetServer>();


    public Server(ServerSocket serverSocket, Socket socol, DataInputStream entrada, DataOutputStream sortida, String usuari, TextArea txtArea) {
        this.serverSocket = serverSocket;
        this.socol = socol;
        this.entrada = entrada;
        this.sortida = sortida;
        this.usuari = usuari;
        this.txtArea = txtArea;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
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

    public TextArea getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(TextArea txtArea) {
        this.txtArea = txtArea;
    }

    @Override
    public void run() {
        try {
            txtArea.appendText("Connectant... \n");
            serverSocket = new ServerSocket(12000);
            while (true) {
                txtArea.appendText("\n\n Esperant als usuaris... \n");
                socol = serverSocket.accept();
                entrada = new DataInputStream(socol.getInputStream());
                sortida = new DataOutputStream(socol.getOutputStream());
                sortida.flush();
                String user = entrada.readUTF();
                txtArea.clear();
                txtArea.appendText("\n S'ha connectat: " + user+"\n");
                GetServer serverRebre = new GetServer(socol, entrada, sortida, user, txtArea);
                Clients.add(serverRebre);
                Thread fil = new Thread(serverRebre);
                fil.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

