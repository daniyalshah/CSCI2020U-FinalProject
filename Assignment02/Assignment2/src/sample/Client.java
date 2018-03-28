package sample;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.miginfocom.layout.Grid;


public class Client extends Application {
    private static String[] cmdArgs;
    private TableView<Data> clientTable;
    private TableView<Data> serverTable;
    private TableColumn<Data, String> clientCol;
    private TableColumn<Data, String> serverCol;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Lets first setup the design of the program
        Group layer = new Group();
        BorderPane layout = new BorderPane();

        //Buttons
        Button upload = new Button("Upload");
        Button download = new Button("Download");

        //

        //Setting up GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(upload, 0, 0);
        gridPane.add(download, 1, 0);
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

        //Finish up the layout
        layer.getChildren().addAll(layout);

        primaryStage.setTitle("SECRET MANGOS .TXT SHARER");
        Scene scene = new Scene(layer, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*
    public class Data {
        private File selectedFile;
        private String fileName;

        public Data(String fileName) {
            this.fileName = fileName;
        }
        public Data(File selectedFile) {
            this.selectedFile = selectedFile;
            this.fileName = selectedFile.getName();
        }
        public String getFileName() {
            return fileName;
        }
        public File getSelectedFile() {
            return selectedFile;
        }
    }
    */

    public static void main(String[] args) {
        cmdArgs = args;
        launch(args);
    }
}
