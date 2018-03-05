/*
Assignment01 - Main.java
Mustafa Al-Azzawe 100617392
Daniyal Syed Shah 100622173
*/

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a Directory");
        directoryChooser.setInitialDirectory(new File("."));

        File currDirectory = directoryChooser.showDialog(primaryStage);
        File trainDir = new File(currDirectory + "/train");
        File testDir = new File(currDirectory + "/test");

        Counter w = new Counter();
        w.searchDirectory(trainDir);
        w.calcProb();
        w.searchDirectory(testDir);

        controller.setAccuracy(Double.toString(w.calcAccuracy()));
        controller.setPrecision(Double.toString(w.calcPrecision()));

        primaryStage.setTitle("Mangoes Spam Detector");
        primaryStage.setScene(new Scene(root, 620, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
