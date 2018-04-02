package chatclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import gui.DrawScreen;
import gui.MainScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import physicsfx.PhysicsFX;
import gui.MainScreen;
import chatclient.ClientLayoutController;

public class ClientLayoutController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField textFieldSend;
    @FXML
    private TextField textFieldName;

    @FXML
    private ListView<String> listViewMessages;

    private ObservableList<String> sendMessageData= FXCollections.observableArrayList();

    private Socket socket;
    private BufferedReader input = null;
    private BufferedWriter output = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewMessages.setItems(sendMessageData);
    }
    MainScreen root = new MainScreen(this);
    @FXML
    private void loadSecond(ActionEvent event) throws IOException{
        rootPane.getChildren().add(root);
    }

    @FXML
    private void handleConnectionButton(){

        try {
            socket = new Socket("localhost",4444);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.flush();
            sendMessageData.add("ready for connection");

        } catch (IOException e) {
            System.out.println("unable to connect");
            //e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            try {
                while(true){ //input.ready()

                    //sendMessageData.add("thread started");
                    String readerInput;
                    if((readerInput = input.readLine()) != null){


                        Platform.runLater( () -> sendMessageData.add(readerInput));
                        System.out.println(readerInput);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @FXML
    private void handleSendMessageBuuton(ActionEvent event) throws IOException{
        if(!textFieldSend.getText().equals("")){

            output.write(textFieldName.getText() +": "+textFieldSend.getText()+"\n");
            output.flush();
            //sendMessageData.add(textFieldSend.getText());

            textFieldSend.clear();
        }

    }

    @FXML
    private void handleFileShareBuuton(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();
        fc.setTitle("Open a Picture");
        //fc.setInitialDirectory(new File("."));
        //fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("", ""));
        File file = fc.showOpenDialog(Client.primaryStage);
        if (file != null) {
            //imageFiles.setAll(load(file.toPath()));
        }
    }
/*
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
        MainScreen mainScreen = new MainScreen(this);
    }

    /*
    private List<Path> load(Path directory) {
        List<Path> files = new ArrayList<>();
        try {
            Files.newDirectoryStream(directory, "*.{jpg,jpeg,png,JPG,JPEG,PNG}").forEach(file -> files.add(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
*/
}