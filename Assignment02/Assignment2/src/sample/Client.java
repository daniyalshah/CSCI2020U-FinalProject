package sample;

import javafx.geometry.Insets;
import javafx.scene.Group;
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Lets first setup the design of the program
        Group layer = new Group();
        BorderPane layout = new BorderPane();

        //Buttons
        Button upload = new Button("Upload");
        Button download = new Button("Download");

        //Setting up GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.add(upload,0,0);
        gridPane.add(download,1,0);
        layout.setTop(gridPane);

        //Setting up TableView

        
















    }
}
