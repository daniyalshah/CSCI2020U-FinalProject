/*
 * Final Project 2020 - Program UI
 * Group Members -
 * Mustafa Al-Azzawe
 * Syed Daniyal Shah
 * Shane Rego
 */

package sample.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import sample.client.Client;
import sample.particle_effect.MainScreen;
//import sample.tictactoe.TicTacToe;

public class chatUIBase extends VBox {

    private final SplitPane splitPane;
    private final AnchorPane particlePane;
    //private final Button loadGame;
    //private final Button loadParticle;
    private final AnchorPane chatPane;
    private final ListView chatListView;
    private final TextField chatTextField;
    private final Button btnSend;
    private final ImageView sendImage;
    private final ImageView mangoImage;
    private final Button btnDisconnect;

    public chatUIBase(Client client) {

        MainScreen particle = new MainScreen(client);
        //TicTacToe game = new TicTacToe();

        splitPane = new SplitPane();
        particlePane = new AnchorPane();
        //loadGame = new Button();
        //loadParticle = new Button();
        chatPane = new AnchorPane();
        chatListView = new ListView<String>();
        chatTextField = new TextField();
        btnSend = new Button();
        sendImage = new ImageView();
        mangoImage = new ImageView();
        btnDisconnect = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(450.0);
        setPrefWidth(705.0);
        setStyle("-fx-background-color: #23262E;");

        VBox.setVgrow(splitPane, javafx.scene.layout.Priority.ALWAYS);
        splitPane.setDividerPositions(0.2505567928730512);
        splitPane.setFocusTraversable(true);
        splitPane.setMaxHeight(USE_PREF_SIZE);
        splitPane.setMaxWidth(USE_PREF_SIZE);
        splitPane.setMinHeight(USE_PREF_SIZE);
        splitPane.setMinWidth(USE_PREF_SIZE);
        splitPane.setPrefHeight(400.0);
        splitPane.setPrefWidth(705.0);

        SplitPane.setResizableWithParent(particlePane, false);
        particlePane.setMaxHeight(USE_PREF_SIZE);
        particlePane.setMaxWidth(USE_PREF_SIZE);
        particlePane.setMinHeight(USE_PREF_SIZE);
        particlePane.setMinWidth(USE_PREF_SIZE);
        particlePane.setPrefHeight(400.0);
        particlePane.setPrefWidth(400.0);

        /*
        loadGame.setLayoutX(79.0);
        loadGame.setLayoutY(201.0);
        loadGame.setMnemonicParsing(false);
        loadGame.setStyle("-fx-background-color: #FF8696; -fx-background-radius: 30px;");
        loadGame.setText("Play Game");
        loadGame.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        loadGame.setFont(new Font(14.0));
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                particlePane.getChildren().add(game);
            }

        });
        */

        /*
        loadParticle.setLayoutX(200.0);
        loadParticle.setLayoutY(201.0);
        loadParticle.setMnemonicParsing(false);
        loadParticle.setStyle("-fx-background-color: #FF8696; -fx-background-radius: 30px;");
        loadParticle.setText("Chill Particle");
        loadParticle.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        loadParticle.setFont(new Font(14.0));
        loadParticle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                particlePane.getChildren().add(particle);
            }

        });
        */

        SplitPane.setResizableWithParent(chatPane, false);
        chatPane.setMaxHeight(USE_PREF_SIZE);
        chatPane.setMaxWidth(USE_PREF_SIZE);
        chatPane.setMinHeight(USE_PREF_SIZE);
        chatPane.setMinWidth(USE_PREF_SIZE);
        chatPane.setPrefHeight(400.0);
        chatPane.setPrefWidth(305.0);

        chatListView.setItems(client.chatLog);
        chatListView.setMaxHeight(USE_PREF_SIZE);
        chatListView.setMaxWidth(USE_PREF_SIZE);
        chatListView.setMinHeight(USE_PREF_SIZE);
        chatListView.setMinWidth(USE_PREF_SIZE);
        chatListView.setPrefHeight(400.0);
        chatListView.setPrefWidth(305.0);

        chatTextField.setMaxHeight(USE_PREF_SIZE);
        chatTextField.setMaxWidth(USE_PREF_SIZE);
        chatTextField.setMinHeight(USE_PREF_SIZE);
        chatTextField.setMinWidth(USE_PREF_SIZE);
        chatTextField.setPrefColumnCount(0);
        chatTextField.setPrefHeight(26.0);
        chatTextField.setPrefWidth(230.0);
        chatTextField.setPromptText("Type a Message..");
        chatTextField.setTranslateX(405.0);
        chatTextField.setTranslateY(12.0);
        chatTextField.setFont(new Font(12.0));

        btnSend.setMaxHeight(USE_PREF_SIZE);
        btnSend.setMaxWidth(USE_PREF_SIZE);
        btnSend.setMinHeight(USE_PREF_SIZE);
        btnSend.setMinWidth(USE_PREF_SIZE);
        btnSend.setMnemonicParsing(false);
        btnSend.setPrefHeight(26.0);
        btnSend.setPrefWidth(50.0);
        btnSend.setStyle("-fx-background-radius: 30px; -fx-background-color: #FF8696;");
        btnSend.setTranslateX(645.0);
        btnSend.setTranslateY(-14.0);
        btnSend.setFont(new Font(12.0));
        btnSend.setDefaultButton(true);
        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.writeToServer(chatTextField.getText());
                chatTextField.clear();
            }
        });

        sendImage.setFitHeight(15.0);
        sendImage.setFitWidth(40.0);
        sendImage.setPickOnBounds(true);
        sendImage.setPreserveRatio(true);
        sendImage.setImage(new Image(getClass().getResource("../../images/send.png").toExternalForm()));
        btnSend.setGraphic(sendImage);

        mangoImage.setFitHeight(26.0);
        mangoImage.setFitWidth(40.0);
        mangoImage.setPickOnBounds(true);
        mangoImage.setPreserveRatio(true);
        mangoImage.setTranslateX(370.0);
        mangoImage.setTranslateY(-40.0);
        mangoImage.setImage(new Image(getClass().getResource("../../images/Peach.png").toExternalForm()));

        btnDisconnect.setMaxHeight(USE_PREF_SIZE);
        btnDisconnect.setMaxWidth(USE_PREF_SIZE);
        btnDisconnect.setMinHeight(USE_PREF_SIZE);
        btnDisconnect.setMinWidth(USE_PREF_SIZE);
        btnDisconnect.setMnemonicParsing(false);
        btnDisconnect.setPrefHeight(26.0);
        btnDisconnect.setPrefWidth(90.0);
        btnDisconnect.setStyle("-fx-background-radius: 15px; -fx-background-color: #FF8696;");
        btnDisconnect.setText("Disconnect");
        btnDisconnect.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));
        btnDisconnect.setTranslateX(10.0);
        btnDisconnect.setTranslateY(-66.0);
        btnDisconnect.setFont(new Font(12.0));
        btnDisconnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                try {
                    client.disconnect();
                    System.exit(0);
                } catch (Exception e) { }
            }
        });

        //particlePane.getChildren().add(loadGame);
        //particlePane.getChildren().add(loadParticle);
        particlePane.getChildren().add(particle);
        splitPane.getItems().add(particlePane);
        chatPane.getChildren().add(chatListView);
        splitPane.getItems().add(chatPane);
        getChildren().add(splitPane);
        getChildren().add(chatTextField);
        getChildren().add(btnSend);
        getChildren().add(mangoImage);
        getChildren().add(btnDisconnect);
    }
}
