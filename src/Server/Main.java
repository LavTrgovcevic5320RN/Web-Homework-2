package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static final int PORT = 9001;

    public static void main(String[] args) throws IOException {
        ArrayList<ServerThread> clientList = new ArrayList<>();
        ServerSocket serversocket = new ServerSocket(PORT);

        try {
            while(true) {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket, clientList);
                clientList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

