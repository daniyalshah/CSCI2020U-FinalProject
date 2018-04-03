/*
 * Final Project 2020 - Program UI
 * Group Members -
 * Mustafa Al-Azzawe
 * Syed Daniyal Shah
 * Shane Rego
 */

package sample.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import sample.server.Server;

public class initServerUI extends VBox {

    private final AnchorPane anchorPane;
    private final Label portText;
    private final TextField portTextField;
    private final Button portApporvalButton;
    private final Label errorLabel;

    public initServerUI(Stage stage) {
        ArrayList<Thread> threads = new ArrayList<Thread>();

        anchorPane = new AnchorPane();
        portText = new Label();
        portTextField = new TextField();
        portApporvalButton = new Button();
        errorLabel = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(300.0);
        setPrefWidth(400.0);

        anchorPane.setPrefHeight(300.0);
        anchorPane.setPrefWidth(400.0);
        anchorPane.setStyle("-fx-background-color: #23262E;");

        portText.setLayoutX(100.0);
        portText.setLayoutY(101.0);
        portText.setText("Port Number: ");
        portText.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        portTextField.setLayoutX(100.0);
        portTextField.setLayoutY(124.0);
        portTextField.setPrefHeight(26.0);
        portTextField.setPrefWidth(200.0);
        portTextField.setPromptText("4444");

        portApporvalButton.setDefaultButton(true);
        portApporvalButton.setLayoutX(100.0);
        portApporvalButton.setLayoutY(160.0);
        portApporvalButton.setMnemonicParsing(false);
        portApporvalButton.setStyle("-fx-background-color: #FF8696; -fx-background-radius: 30px;");
        portApporvalButton.setText("Start");
        portApporvalButton.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        portApporvalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Server server = new Server(Integer.parseInt(portTextField
                            .getText()));
                    Thread serverThread = (new Thread(server));
                    serverThread.setName("Server Thread");
                    serverThread.setDaemon(true);
                    serverThread.start();
                    threads.add(serverThread);

                    stage.hide();
                    stage.setScene(makeServerUI(server));
                    stage.show();
                }catch(IllegalArgumentException e){
                    errorLabel.setText("invalid port number!");
                }
                catch (IOException e) { }
            }
        });

        errorLabel.setLayoutX(100.0);
        errorLabel.setLayoutY(198.0);
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setFont(new Font(12.0));

        anchorPane.getChildren().add(portText);
        anchorPane.getChildren().add(portTextField);
        anchorPane.getChildren().add(portApporvalButton);
        anchorPane.getChildren().add(errorLabel);
        getChildren().add(anchorPane);
    }

    public Scene makeServerUI(Server server){
        serverUIBase root = new serverUIBase(server);

        return new Scene(root, 400, 600);
    }
}
