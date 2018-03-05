package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListEmail {

    private static ObservableList<TestFile> emails = FXCollections.observableArrayList();

    public static void setEmail(String file_name, String actual_class, double spam_prob) {
        emails.add(new TestFile(file_name,actual_class,spam_prob));
    }

    public static ObservableList getEmails(){return emails;}
}
