/*
Assignment01 - Controller.java
Mustafa Al-Azzawe 100617392
Syed Daniyal Shah 100622173
*/

package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller implements Initializable{

    //variables for fxml, this class shaped fxml class
    @FXML private TableView<TestFile> table;
    @FXML private TextField accuracyColumn;
    @FXML private TextField precisionColumn;
    @FXML private TableColumn<TestFile,String> filesColumn;
    @FXML private TableColumn<TestFile, String> spamProb;
    @FXML private TableColumn<TestFile, String> actualClassColumn;

    //calling override initialize method from Initializable
    public void initialize(URL location, ResourceBundle resources) {

        //using observable data
        ObservableList emails = Inbox.getEmails();

        accuracyColumn.setText("Accuracy");
        precisionColumn.setText("Precision");
        filesColumn.setCellValueFactory(new PropertyValueFactory<>("Filename"));
        actualClassColumn.setCellValueFactory(new PropertyValueFactory<>("ActualClass"));
        spamProb.setCellValueFactory(new PropertyValueFactory<>("SpamProbRounded"));
        table.setItems(emails);
    }

    public void setAccuracy(String accuracyCol) {
        this.accuracyColumn.setText(accuracyCol);
    }

    public void setPrecision(String precisionCol) {
        this.precisionColumn.setText(precisionCol);
    }
}
