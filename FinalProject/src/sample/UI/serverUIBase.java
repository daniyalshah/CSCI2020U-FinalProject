/*
 * Final Project 2020 - Program UI
 * Group Members -
 * Mustafa Al-Azzawe
 * Syed Daniyal Shah
 * Shane Rego
 */

package sample.UI;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.server.Server;

public class serverUIBase extends VBox {

    private final AnchorPane anchorPane;
    private final Label logLabel;
    private final ListView logView;
    private final Label clientLabel;
    private final ListView clientView;

    public serverUIBase(Server server) {

        anchorPane = new AnchorPane();
        logLabel = new Label();
        logView = new ListView();
        clientLabel = new Label();
        clientView = new ListView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(400.0);

        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(400.0);
        anchorPane.setStyle("-fx-background-color: #23262E;");

        logLabel.setLayoutX(76.0);
        logLabel.setLayoutY(53.0);
        logLabel.setText("Server Log");
        logLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        logView.setLayoutX(76.0);
        logView.setLayoutY(76.0);
        logView.setPrefHeight(210.0);
        logView.setPrefWidth(250.0);
        ObservableList<String> logList = server.serverLog;
        logView.setItems(logList);

        clientLabel.setLayoutX(76.0);
        clientLabel.setLayoutY(315.0);
        clientLabel.setText("Clients Connected");
        clientLabel.setTextFill(javafx.scene.paint.Color.valueOf("#e4e4e4"));

        clientView.setLayoutX(76.0);
        clientView.setLayoutY(340.0);
        clientView.setPrefHeight(210.0);
        clientView.setPrefWidth(250.0);
        ObservableList<String> clientList = server.clientNames;
        clientView.setItems(clientList);

        anchorPane.getChildren().add(logLabel);
        anchorPane.getChildren().add(logView);
        anchorPane.getChildren().add(clientLabel);
        anchorPane.getChildren().add(clientView);
        getChildren().add(anchorPane);

    }
}
