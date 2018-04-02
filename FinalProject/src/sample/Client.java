package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Client implements Runnable {

    private Socket clientSocket;
    private BufferedReader serverToClientReader;
    private PrintWriter clientToServerWriter;
    private String username;
    public ObservableList<String> chatLog;

    public Client(String hostAddress, int portNum, String username) throws UnknownHostException, IOException {
        clientSocket = new Socket(hostAddress, portNum);
        serverToClientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientToServerWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        chatLog = FXCollections.observableArrayList();

        //send the clients name to the server
        this.username = username;
        clientToServerWriter.println(username);
    }

    public void writeToServer(String input) {
        clientToServerWriter.println(username + " : " + input);
    }

    public void run() {
        //looping to update the chat log from the server
        while (true) {
            try {
                final String inputFromServer = serverToClientReader.readLine();
                Platform.runLater(new Runnable() {
                    public void run() {
                        chatLog.add(inputFromServer);
                    }
                });
            } catch (SocketException e) {
                Platform.runLater(() -> chatLog.add("oops, looks like something went wrong!"));
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect(){
        try{
            if(clientSocket != null){
                writeToServer(" has left the chat..");
                clientSocket.close();
            }
        }catch (Exception e){ }
        try{
            if(serverToClientReader != null){ serverToClientReader.close(); }
        }catch (Exception e){ }
        try{
            if(clientToServerWriter != null){ clientToServerWriter.close(); }
        }catch (Exception e){ }
    }
}

