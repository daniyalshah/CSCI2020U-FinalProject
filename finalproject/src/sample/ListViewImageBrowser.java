package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ListViewImageBrowser extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Path> imageFiles = FXCollections.observableArrayList();

        TextField directoryField = new TextField();
        Button browseButton = new Button("Browse");
        Button loadButton = new Button("Load");

        EventHandler<ActionEvent> loadHandler = event -> imageFiles.setAll(load(Paths.get(directoryField.getText())));
        loadButton.setOnAction(loadHandler);
        directoryField.setOnAction(loadHandler);

        browseButton.setOnAction(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File file = chooser.showDialog(primaryStage);
            if (file != null) {
                directoryField.setText(file.toString());
                imageFiles.setAll(load(file.toPath()));
            }
        });

        HBox controls = new HBox(5, directoryField, browseButton, loadButton);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(5));

        ListView<Path> imageFilesList = new ListView<>(imageFiles);
        imageFilesList.setCellFactory(listView -> new ListCell<Path>() {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(Path path, boolean empty) {
                super.updateItem(path, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(path.getFileName().toString());
                    imageView.setImage(new Image(path.toUri().toString(), 80, 160, true, true, true));
                    setGraphic(imageView);
                }
            }
        });


        ImageView imageView = new ImageView();
        imageFilesList.getSelectionModel().selectedItemProperty().addListener((obs, oldFile, newFile) -> {
            if (newFile == null) {
                imageView.setImage(null);
            } else {
                imageView.setImage(new Image(newFile.toUri().toString(), true));
            }
        });
        imageFilesList.setMinWidth(200);

        StackPane imageHolder = new StackPane(imageView);
        imageView.fitWidthProperty().bind(imageHolder.widthProperty());
        imageView.fitHeightProperty().bind(imageHolder.heightProperty());
        imageView.setPreserveRatio(true);

        BorderPane root = new BorderPane(imageHolder, controls, null, null, imageFilesList);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Path> load(Path directory) {
        List<Path> files = new ArrayList<>();
        try {
            Files.newDirectoryStream(directory, "*.{jpg,jpeg,png,JPG,JPEG,PNG}").forEach(file -> files.add(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
