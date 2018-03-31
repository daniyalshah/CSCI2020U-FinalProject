package sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socketServer;
    private int clientNumber;
    private BufferedWriter output;
    private BufferedReader input;
    private Server mainServer;

    public ServerThread(Socket socketServer, int clientNumber, Server server) {
        this.socketServer = socketServer;
        this.clientNumber = clientNumber;
        this.mainServer = server;
        System.out.println("New Client number " +clientNumber+" connected!");
    }




    public void run(){
        try {
            input = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream()));
            while(true){
                String message = input.readLine();
                System.out.println("message: "+message);
                mainServer.sendMessage(this, message + "\n");
                output.flush();
            }


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public BufferedWriter getOutput() {
        return output;
    }


}
