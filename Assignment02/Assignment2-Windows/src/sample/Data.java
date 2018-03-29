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
