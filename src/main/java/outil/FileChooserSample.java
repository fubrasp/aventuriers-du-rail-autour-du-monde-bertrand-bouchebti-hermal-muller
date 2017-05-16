package outil;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileChooserSample {

    private Desktop desktop = Desktop.getDesktop();

    public static File selectFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fileSelected = fileChooser.showOpenDialog(stage);
        return fileSelected;
    }

    public static File saveFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        File fileSelected = fileChooser.showSaveDialog(stage);
        return fileSelected;
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}
