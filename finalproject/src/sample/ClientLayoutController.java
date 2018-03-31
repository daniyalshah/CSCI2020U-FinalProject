package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ClientLayoutController implements Initializable {

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
                // TODO Auto-generated catch block
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



}