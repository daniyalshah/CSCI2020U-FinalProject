/*
 * Final Project 2020 - Server Client
 * Group Members -
 * Shane Rego
 * Mustafa Al-Azzawe
 * Syed Daniyal Shah
 */

package sample.server;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    private int portNumber;

    private ServerSocket socket;

    private ArrayList<Socket> clients;
    private ArrayList<ClientThread> clientThreads;
    public ObservableList<String> serverLog;
    public ObservableList<String> clientNames;

    public Server(int portNumber) throws IOException {
        this.portNumber = portNumber;
        socket = new ServerSocket(portNumber);
        serverLog = FXCollections.observableArrayList();
        clientNames = FXCollections.observableArrayList();
        clients = new ArrayList<Socket>();
        clientThreads = new ArrayList<ClientThread>();
    }

    public void startServer() {
        try {
            socket = new ServerSocket(this.portNumber);
            serverLog = FXCollections.observableArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            //looping to accept any incoming connection requests
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverLog.add("searching for connection...");
                    }
                });

                final Socket clientSocket = socket.accept();

                //add the incoming socket connection to the list of clients
                clients.add(clientSocket);
                //add to log that a client connected
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverLog.add("client "
                                + clientSocket.getRemoteSocketAddress()
                                + " connected");
                    }
                });
                ClientThread clientThreadHolderClass = new ClientThread(clientSocket, this);
                Thread clientThread = new Thread(clientThreadHolderClass);
                clientThreads.add(clientThreadHolderClass);
                clientThread.setDaemon(true);
                clientThread.start();
                ServerApp.threads.add(clientThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clientDisconnected(ClientThread client) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                serverLog.add("client "
                        + client.getClientSocket().getRemoteSocketAddress()
                        + " disconnected");
                clients.remove(clientThreads.indexOf(client));
                clientNames.remove(clientThreads.indexOf(client));
                clientThreads.remove(clientThreads.indexOf(client));
            }
        });
    }

    public void writeToAllSockets(String input) {
        for (ClientThread clientThread : clientThreads) {
            clientThread.writeToServer(input);
        }
    }
}