/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
int counter=0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="counterTF"
    private TextField counterTF; // Value injected by FXMLLoader

    @FXML // fx:id="helloBtn"
    private Button helloBtn; // Value injected by FXMLLoader

    @FXML // fx:id="helloTF"
    private TextField helloTF; // Value injected by FXMLLoader


    @FXML
    void sayHello(ActionEvent event) {
counter++;
counterTF.setText(Integer.toString(counter));
if(counter%2==0){
    helloTF.setText("");
}
else{
    helloTF.setText("hello world");
}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert counterTF != null : "fx:id=\"counterTF\" was not injected: check your FXML file 'primary.fxml'.";
        assert helloBtn != null : "fx:id=\"helloBtn\" was not injected: check your FXML file 'primary.fxml'.";
        assert helloTF != null : "fx:id=\"helloTF\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
