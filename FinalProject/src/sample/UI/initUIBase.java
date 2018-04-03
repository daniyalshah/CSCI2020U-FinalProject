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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

import sample.client.Client;

public class initUIBase extends VBox {

    private final AnchorPane anchorPane;
    private final TextField userNameField;
    private final TextField hostAddressField;
    private final TextField portNumField;
    private final Label nameLabel;
    private final Label hostAddressLabel;
    private final Label portNumLabel;
    private final Button btnConnect;
    private final Label errorLabel;

    public initUIBase(Stage stage) {

        ArrayList<Thread> threads = new ArrayList<Thread>();

        anchorPane = new AnchorPane();
        userNameField = new TextField();
        hostAddressField = new TextField();
        portNumField = new TextField();
        nameLabel = new Label();
        hostAddressLabel = new Label();
        portNumLabel = new Label();
        btnConnect = new Button();
        errorLabel = new Label();

        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(400.0);
        setPadding(new Insets(20.0));

        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(USE_PREF_SIZE);
        anchorPane.setMinHeight(USE_PREF_SIZE);
        anchorPane.setMinWidth(USE_PREF_SIZE);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(400.0);
        anchorPane.setStyle("-fx-background-color: #23262E;");
        anchorPane.setPadding(new Insets(20.0));

        userNameField.setLayoutX(166.0);
        userNameField.setLayoutY(131.0);

        hostAddressField.setLayoutX(166.0);
        hostAddressField.setLayoutY(174.0);
        hostAddressField.setPromptText("127.0.0.1");

        portNumField.setLayoutX(166.0);
        portNumField.setLayoutY(217.0);
        portNumField.setPromptText("4444");

        nameLabel.setLayoutX(62.0);
        nameLabel.setLayoutY(136.0);
        nameLabel.setText("Username: ");
        nameLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        hostAddressLabel.setLayoutX(62.0);
        hostAddressLabel.setLayoutY(179.0);
        hostAddressLabel.setText("Host Address: ");
        hostAddressLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        portNumLabel.setLayoutX(62.0);
        portNumLabel.setLayoutY(222.0);
        portNumLabel.setText("Port Number: ");
        portNumLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        btnConnect.setDefaultButton(true);
        btnConnect.setLayoutX(265.0);
        btnConnect.setLayoutY(260.0);
        btnConnect.setMnemonicParsing(false);
        btnConnect.setStyle("-fx-background-color: #FF8696; -fx-background-radius: 30px;");
        btnConnect.setText("Connect");
        btnConnect.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        btnConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Client client;
                try {
                    client = new Client(hostAddressField.getText(), Integer
                            .parseInt(portNumField.getText()), userNameField
                            .getText());
                    Thread clientThread = new Thread(client);
                    clientThread.setDaemon(true);
                    clientThread.start();
                    threads.add(clientThread);

                    stage.close();
                    stage.setScene(goChatUI(client));
                    stage.show();
                }
                catch(ConnectException e){
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("invalid host address, try again!");
                }
                catch (NumberFormatException | IOException e) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("invalid port number, try again!");
                }
            }
        });

        errorLabel.setLayoutX(62.0);
        errorLabel.setLayoutY(265.0);
        errorLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        errorLabel.setFont(new Font(11.0));

        anchorPane.getChildren().add(userNameField);
        anchorPane.getChildren().add(hostAddressField);
        anchorPane.getChildren().add(portNumField);
        anchorPane.getChildren().add(nameLabel);
        anchorPane.getChildren().add(hostAddressLabel);
        anchorPane.getChildren().add(portNumLabel);
        anchorPane.getChildren().add(btnConnect);
        anchorPane.getChildren().add(errorLabel);
        getChildren().add(anchorPane);

    }

    public Scene goChatUI(Client client){
        chatUIBase root = new chatUIBase(client);

        return new Scene(root);
    }
}

