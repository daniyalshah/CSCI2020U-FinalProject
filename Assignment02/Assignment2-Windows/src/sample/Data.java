/*
Syed Daniyal Shah - 100622173
Mustafa Al-Azzawe - 100617392
Assignment02 - Data.java
 */

package sample;

import java.io.File;

public class Data {
    private File selectedFile;
    private String fileName;

    public Data(String fileName) {
        this.fileName = fileName;
    }
    public Data(File selectedFile) {
        this.selectedFile = selectedFile;
        this.fileName = selectedFile.getName();
    }
    public String getFileName() {
        return fileName;
    }
    public File getSelectedFile() {
        return selectedFile;
    }
}
