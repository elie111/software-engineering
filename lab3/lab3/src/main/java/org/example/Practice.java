/**
 * Sample Skeleton for 'practice.fxml' Controller Class
 */

package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Practice {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listBox"
    private ComboBox<String> listBox; // Value injected by FXMLLoader

    @FXML
    void chooseFromList(ActionEvent event) {
        String chosen = listBox.getSelectionModel().getSelectedItem();
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        listBox.getItems().add("first");
        listBox.getItems().add("second");
        assert listBox != null : "fx:id=\"listBox\" was not injected: check your FXML file 'practice.fxml'.";

    }

}
