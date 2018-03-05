/*
Assignment01 - Inbox.java
Mustafa Al-Azzawe 100617392
Daniyal Syed Shah 100622173
*/

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inbox {

    //add values to observablelist
    private static ObservableList<TestFile> emails = FXCollections.observableArrayList();

    public static void setEmail(String fileName, double spamProb, String actualClass) {
        emails.add(new TestFile(fileName, spamProb, actualClass));
    }

    //returning obersvable list
    public static ObservableList getEmails(){ return emails; }
}