package chatclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import physicsfx.PhysicsFX;
import gui.MainScreen;

public class Client extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("clientLayout.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            //runAnotherApp(PhysicsFX.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception {
        Application app2 = anotherAppClass.newInstance();
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }


    public static void main(String[] args) {
        launch(args);
    }


}
