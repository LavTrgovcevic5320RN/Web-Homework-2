package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int PORT = 9001;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("localhost", PORT);
            String username = "";
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String input, a, b;
            Scanner scanner = new Scanner(System.in);

            ClientThread clientRun = new ClientThread(socket);
            Thread thread = new Thread(clientRun);
            thread.start();

            while (true) {
                if (username.length() < 1) {
                    System.out.println("Enter your username: ");
                    input = scanner.nextLine();
                    while (input.length() < 1) {
                        System.out.println("Username cant be blank");
                        input = scanner.nextLine();
                    }
                    username = input;
                    out.println(input);
                } else {
                    input = scanner.nextLine();
                    while (input.length() < 1) {
                        input = scanner.nextLine();
                    }
                    out.println(">" + input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
