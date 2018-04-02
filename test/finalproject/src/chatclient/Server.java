package chatclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private Integer PORT = 4444;
    List<ServerThread> list = Collections.synchronizedList(new ArrayList());

    public static void main(String[] args) throws IOException {
        new Server().runServer();
    }

    private void runServer() throws IOException {
        ServerSocket listener = null;
        System.out.println("Server waiting for connections..");
        int clintNumber = 0;
        try {
            listener = new ServerSocket(PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                Socket socketServer = listener.accept();

                ServerThread serverThread = new ServerThread(socketServer, clintNumber++, this);

                serverThread.start();
                list.add(serverThread);
            }
        } finally {
            listener.close();
            System.out.println("listener is closed!");
        }
    }

    public void sendMessage(ServerThread serverThread, String message) throws IOException {
        for (ServerThread threads : list) {
            BufferedWriter outputStream = threads.getOutput();
            outputStream.write(message);
            outputStream.flush();
        }

    }


}


