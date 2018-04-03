/*
 * Final Project 2020 - Server Client
 * Group Members -
 * Shane Rego
 * Mustafa Al-Azzawe
 * Syed Daniyal Shah
 */

package sample.server;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.UI.initServerUI;

import java.io.IOException;
import java.util.ArrayList;

public class ServerApp extends Application {
    public static ArrayList<Thread> threads;
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        threads = new ArrayList<Thread>();

        primaryStage.setTitle("Mangos Chat Server");
        primaryStage.setScene(makePortUI(primaryStage));
        primaryStage.show();
    }


    public Scene makePortUI(Stage primaryStage) {
        initServerUI root = new initServerUI(primaryStage);

        return new Scene(root, 400, 300);
    }
}
