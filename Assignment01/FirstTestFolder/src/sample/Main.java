package sample;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        //loading the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        //


    }


    public static void main(String[] args) {
        launch(args);
    }
}
