package physicsfx;

import gui.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PhysicsFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainScreen root = new MainScreen(this);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("THIS IS SICK AF MOOSE LOL");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {root.stopGameLoop(); });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
