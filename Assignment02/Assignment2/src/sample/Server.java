package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private File serverDirectory;
    private ArrayList<Data> dataRecordArrayList;
    private File[] filesList;
    BufferedReader fileIn;

    public static void main(String[] args) {
        new Server().server();
    }

    public void server() {
        //get files from server folder
        serverDirectory = new File("./serverFiles");

        // start connection
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                System.out.println("listening for fruits");
                clientSocket = serverSocket.accept();
                System.out.println("connected to fruits");
                Thread thread = new Thread(new ClientConnectionHandler());
                thread.start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    //our connection handler for commmands via
    public class ClientConnectionHandler implements Runnable {
        @Override
        public void run() {
            getClientCommand();
        }

        // command from server-client
        private synchronized void getClientCommand() {
            String clientCommand;
            try {
                fileIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                clientCommand = fileIn.readLine();
                //for command tokens
                String clientCommandTokens[] = clientCommand.split(" ");
                for (int i = 0; i < clientCommandTokens.length; i++) {
                    String entry = clientCommandTokens[i];
                    System.out.println("clientCommandTokens[" + i + "] = " + clientCommandTokens[i]);
                }
                if (clientCommandTokens[0].equals("DIR")) {
                    System.out.println("received command: " + clientCommandTokens[0]);
                    sendFilesDIR();
                } else if (clientCommandTokens[0].equals("UPLOAD")) {
                    System.out.println("received command: " + clientCommandTokens[0]);
                    uploadFile(clientCommandTokens[1]);
                } else if (clientCommandTokens[0].equals("DOWNLOAD")) {
                    System.out.println("received command: " + clientCommandTokens[0]);
                    downloadFile(clientCommandTokens[1]);
                } else {
                    System.out.println("unknown command");
                    clientSocket.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        private synchronized void sendFilesDIR() {
            // put it in array, for "DIR"
            dataRecordArrayList = new ArrayList<>();
            filesList = serverDirectory.listFiles();
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                //out.println("Heres a files list");
                for (File entry : filesList) {
                    System.out.println("entry.geName() = " + entry.getName());
                    writer.println(entry.getName());
                    writer.flush();
                    dataRecordArrayList.add(new Data(entry));
                }
                writer.println("\0");
                writer.flush();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        private synchronized void uploadFile(String fileName) {
            System.out.println("getting file name " + fileName);
            File receiveFile = new File(serverDirectory.getPath() + "/" + fileName);
            System.out.println("file made " + serverDirectory.getPath() + "/" + fileName);
            String line;
            try {
                PrintWriter writer2 = new PrintWriter(receiveFile);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while ((line = fileIn.readLine()) != null) {
                    if (line.equals("\0")) {
                        break;
                    }
                    System.out.println("your file says: " + line);
                    writer2.println(line);
                    writer2.flush();
                }
                writer2.close();
                System.out.println("file uploaded to mother fruits");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        private synchronized void downloadFile(String fileName) {
            int index = -1;
            System.out.println("fileName = " + fileName);
            for (Data entry : dataRecordArrayList) {
                System.out.println("entry.getFileName() = " + entry.getFileName());
                if (entry.getFileName().equals(fileName)) {
                    System.out.println("found");
                    index = dataRecordArrayList.indexOf(entry);
                }
            }
            String line;
            try {
                fileIn = new BufferedReader(new FileReader(serverDirectory + "/" + dataRecordArrayList.get(index).getFileName()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                while ((line = fileIn.readLine()) != null) {
                    out.println(line);
                    out.flush();
                }
                out.println("\0");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}





