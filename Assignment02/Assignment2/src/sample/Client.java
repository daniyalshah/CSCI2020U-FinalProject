package sample;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TablePosition;
import java.io.*;
import javafx.event.EventHandler;
import java.net.DatagramSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.miginfocom.layout.Grid;
import org.apache.commons.httpclient.util.IdleConnectionHandler;


public class Client extends Application {
    private static String[] cmdArgs;
    private TableView<Data> clientTable;
    private TableView<Data> serverTable;
    private TableColumn<Data, String> clientCol;
    private TableColumn<Data, String> serverCol;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer, writer1;
    private File myDir;
    private BufferedReader fileIn;
    private ConnectionHandler connectionHandler;




    @Override
    public void start(Stage primaryStage) throws Exception {

        //Lets first setup the design of the program
        Group layer = new Group();
        BorderPane layout = new BorderPane();

        //Buttons
        Button uploadB = new Button("Upload");
        Button downloadB = new Button("Download");

        //Setting up GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(uploadB, 0, 0);
        gridPane.add(downloadB, 1, 0);
        layout.setTop(gridPane);

        //Setting up TableView/Column for Client (left side)
        clientTable = new TableView<>();
        clientCol = new TableColumn<>();
        clientCol.setCellValueFactory(new PropertyValueFactory<>("--"));
        clientCol.setMinWidth(300);
        clientTable.getColumns().addAll(clientCol);

        layout.setLeft(clientTable);

        //Setting up TableView/Column for Server (right side)
        serverTable = new TableView<>();
        serverCol = new TableColumn<>();
        serverCol.setCellValueFactory(new PropertyValueFactory<>("--"));
        serverCol.setMinWidth(300);
        serverTable.getColumns().addAll(serverCol);

        layout.setRight(clientTable);

        //Button Commands for sending filename to the cell in server or client section
        //Upload Part
        uploadB.setOnAction(e -> {
            TablePosition tablePosition = clientTable.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            Data dataRecord = clientTable.getItems().get(row);
            TableColumn column = tablePosition.getTableColumn();
            String fileName = (String) column.getCellObservableValue(dataRecord).getValue();
            System.out.println("You've selected:" + fileName);
        });

        //Download Part
        downloadB.setOnAction(e -> {
            TablePosition tablePosition = serverTable.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            Data dataRecord = serverTable.getItems().get(row);
            TableColumn column = tablePosition.getTableColumn();
            String fileName = (String) column.getCellObservableValue(dataRecord).getValue();
            System.out.println("You've selected:" + fileName);
        });

        //Finish up the layout
        layer.getChildren().addAll(layout);

        primaryStage.setTitle("SECRET MANGOS .TXT SHARER");
        Scene scene = new Scene(layer, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Start everything
        serverConnect();
        ConnectionHandler conectionHandler = new ConnectionHandler();
        Thread thread = new Thread(conectionHandler);
        thread.start();
        getClientFiles();

    }
    //Get server files to the right side
    public class ConnectionHandler implements Runnable {
        @Override
        public void run() {
            getServerFilesList();
        }
        public synchronized void getServerFilesList(){
            ObservableList<Data> serverFiles = FXCollections.observableArrayList();
            String fileName;
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                writer.println("DIRECTORY");
                writer.flush();
                System.out.println("testing linkage");
                while ((fileName = reader.readLine()) != null) {
                    if (fileName.equals("/0")) {
                        break;
                    }
                    serverFiles.add(new Data(fileName));
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            serverTable.setItems(serverFiles);
        }
    }

    //method during/when connected
    private void serverConnect(){
        try {
            clientSocket = new Socket("localhost", 9999);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream());
            System.out.println("Connected to server");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //left side of data table with files
    private void getClientFiles () {
        ObservableList<Data> clientFiles = FXCollections.observableArrayList();
        myDir = new File("./clientFiles");
        File[] fileList = myDir.listFiles();
        for (File entry : fileList) {
            if (entry.isFile()) {
                clientFiles.addAll(new Data(entry));
            }
            clientTable.setItems(clientFiles);
        }
    }

    //main method
    public static void main(String[] args) {
        cmdArgs = args;
        launch(args);
    }
}
