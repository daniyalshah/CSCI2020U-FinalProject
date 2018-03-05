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

    @FXML
    private TableView<TestFile> table;
    @FXML private TextField accuracy_column;
    @FXML private TextField precision_column;
    @FXML private TableColumn<TestFile,String> files_column;
    @FXML private TableColumn<TestFile, String> spam_prob;
    @FXML private TableColumn<TestFile, String> actual_class;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList emails = EmailList.getEmails();

        accuracy_column.setText("Accuracy");
        precision_column.setText("Precision");
        file_column.setCellValueFactory(new PropertyValueFactory<>("Filename"));
        actual_class.setCellValueFactory(new PropertyValueFactory<>("ActualClass"));
        spam_prob.setCellValueFactory(new PropertyValueFactory<>("SpamProbRounded"));
        table.setItems(emails);
    }

    public void setAccuracy_column(String accuracy) {
        this.accuracy_field.setText(accuracy);
    }

    public void setPrecision_column(String precision) {
        this.precision_field.setText(precision);
    }
}
