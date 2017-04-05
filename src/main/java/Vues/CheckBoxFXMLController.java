package Vues;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckBoxFXMLController implements Initializable {
    @FXML
    CheckBox chksport1;
    @FXML
    CheckBox chksport2;
    @FXML
    CheckBox chksport3;
    @FXML
    CheckBox chksport4;
    @FXML
    Label lbltotal;
    @FXML
    Label lbllist;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        int count=0;
        String choices="";
        if(chksport1.isSelected()){
            count++;
            choices+=chksport1.getText() + "\n";
        }
        if(chksport2.isSelected()){
            count++;
            choices+=chksport2.getText() + "\n";
        }
        if(chksport3.isSelected()){
            count++;
            choices+=chksport3.getText() + "\n";
        }
        if(chksport4.isSelected()){
            count++;
            choices+=chksport4.getText() + "\n";
        }
        lbltotal.setText("Sports chosen: " + count);
        lbllist.setText(choices);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

