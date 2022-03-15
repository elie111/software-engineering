/**
 * Sample Skeleton for 'calculator.fxml' Controller Class
 */

package org.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static org.example.ArithmeticApp.baseConversion;

public class Calculator {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="aBtn"
    private Button aBtn; // Value injected by FXMLLoader

    @FXML // fx:id="bBtn"
    private Button bBtn; // Value injected by FXMLLoader

    @FXML // fx:id="baseBox"
    private ComboBox<String> baseBox; // Value injected by FXMLLoader

    @FXML // fx:id="cBtn"
    private Button cBtn; // Value injected by FXMLLoader

    @FXML // fx:id="clearBtn"
    private Button clearBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dBtn"
    private Button dBtn; // Value injected by FXMLLoader

    @FXML // fx:id="divBtn"
    private Button divBtn; // Value injected by FXMLLoader

    @FXML // fx:id="eBtn"
    private Button eBtn; // Value injected by FXMLLoader

    @FXML // fx:id="eightBtn"
    private Button eightBtn; // Value injected by FXMLLoader

    @FXML // fx:id="equalBtn"
    private Button equalBtn; // Value injected by FXMLLoader

    @FXML // fx:id="fBtn"
    private Button fBtn; // Value injected by FXMLLoader

    @FXML // fx:id="fiveBtn"
    private Button fiveBtn; // Value injected by FXMLLoader

    @FXML // fx:id="fourBtn"
    private Button fourBtn; // Value injected by FXMLLoader

    @FXML // fx:id="minusBtn"
    private Button minusBtn; // Value injected by FXMLLoader

    @FXML // fx:id="mulBtn"
    private Button mulBtn; // Value injected by FXMLLoader

    @FXML // fx:id="nineBtn"
    private Button nineBtn; // Value injected by FXMLLoader

    @FXML // fx:id="oneBtn"
    private Button oneBtn; // Value injected by FXMLLoader

    @FXML // fx:id="plusBtn"
    private Button plusBtn; // Value injected by FXMLLoader

    @FXML // fx:id="resultTF"
    private TextField resultTF; // Value injected by FXMLLoader

    @FXML // fx:id="sevenBtn"
    private Button sevenBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sixBtn"
    private Button sixBtn; // Value injected by FXMLLoader

    @FXML // fx:id="threeBtn"
    private Button threeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="twoBtn"
    private Button twoBtn; // Value injected by FXMLLoader

    @FXML // fx:id="zeroBtn"
    private Button zeroBtn; // Value injected by FXMLLoader
boolean equal=false;
String equation="";
int base=10;
boolean err=false;


    @FXML
    void calcBase(ActionEvent event) {
        resultTF.setText("");
        equation="";
        String chosen = baseBox.getSelectionModel().getSelectedItem();
        zeroBtn.setDisable(false);
        oneBtn.setDisable(false);
        twoBtn.setDisable(false);
        threeBtn.setDisable(false);
        fourBtn.setDisable(false);
        fiveBtn.setDisable(false);

        sixBtn.setDisable(false);
        sevenBtn.setDisable(false);
        eightBtn.setDisable(false);
        nineBtn.setDisable(false);
        aBtn.setDisable(false);
        bBtn.setDisable(false);
        cBtn.setDisable(false);
        dBtn.setDisable(false);
        eBtn.setDisable(false);
        fBtn.setDisable(false);
        switch (chosen){
            case "DEC":
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                dBtn.setDisable(true);
                eBtn.setDisable(true);
                fBtn.setDisable(true);
                base=10;
                break;
            case "BIN" :
                twoBtn.setDisable(true);
                threeBtn.setDisable(true);
                fourBtn.setDisable(true);
                fiveBtn.setDisable(true);

                sixBtn.setDisable(true);
                sevenBtn.setDisable(true);
                eightBtn.setDisable(true);
                nineBtn.setDisable(true);
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                dBtn.setDisable(true);
                eBtn.setDisable(true);
                fBtn.setDisable(true);
                base=2;
                break;
            case "HEX":
                base=16;
                break;
            case "OCT":
                eightBtn.setDisable(true);
                nineBtn.setDisable(true);
                aBtn.setDisable(true);
                bBtn.setDisable(true);
                cBtn.setDisable(true);
                dBtn.setDisable(true);
                eBtn.setDisable(true);
                fBtn.setDisable(true);
                base=2;
                base=8;
                break;

        }
    }
    @FXML
    void calcBtns(ActionEvent event) {

if(err==true){
    equation="";
    resultTF.setText(equation);
    err=false;

} //if there was an error clear the equation
if(((Button)event.getSource())==clearBtn){
    equation="";
    resultTF.setText(equation);
} //if we pressed on clear button clear the equation and the screen
else {
    if (((Button) event.getSource()) == equalBtn) { // if we pressed on the equal button
       if(equation.charAt(0)=='-'){
            char zer='0';
            equation=zer+equation;
        }
        if(ArithmeticApp.checkcorrection(equation,base)) {
            String str = equation;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '+' || str.charAt(i) == '/' || str.charAt(i) == '-' || str.charAt(i) == '*') {
                    str = str.substring(0, i) + ' ' + str.substring(i);
                    str = str.substring(0, i + 2) + ' ' + str.substring(i + 2);
                    i += 2;
                }
            }


            ArithmeticApp.FullCalculator calc = new ArithmeticApp.FullCalculator();
            int res = calc.processInput(str, base);

if(res==-1&&calc.getminusone()==false) {

    err=true;
    equation="error";
}
else{
    equation = baseConversion(Integer.toString(res), 10, base).toUpperCase();
}
}
        else{
            err=true;
            equation="error";
        }
    } else {
        equation += ((Button) event.getSource()).getText();
    }
    resultTF.setText(equation);
}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        baseBox.getItems().add("DEC");
        baseBox.getItems().add("BIN");
        baseBox.getItems().add("HEX");
        baseBox.getItems().add("OCT");

        baseBox.getSelectionModel().selectFirst();
        aBtn.setDisable(true);
        bBtn.setDisable(true);
        cBtn.setDisable(true);
        dBtn.setDisable(true);
        eBtn.setDisable(true);
        fBtn.setDisable(true);
        base=10;

        resultTF.setEditable(false);

        assert aBtn != null : "fx:id=\"aBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert bBtn != null : "fx:id=\"bBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert baseBox != null : "fx:id=\"baseBox\" was not injected: check your FXML file 'calculator.fxml'.";
        assert cBtn != null : "fx:id=\"cBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert dBtn != null : "fx:id=\"dBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert divBtn != null : "fx:id=\"divBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert eBtn != null : "fx:id=\"eBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert eightBtn != null : "fx:id=\"eightBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert equalBtn != null : "fx:id=\"equalBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert fBtn != null : "fx:id=\"fBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert fiveBtn != null : "fx:id=\"fiveBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert fourBtn != null : "fx:id=\"fourBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert minusBtn != null : "fx:id=\"minusBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert mulBtn != null : "fx:id=\"mulBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert nineBtn != null : "fx:id=\"nineBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert oneBtn != null : "fx:id=\"oneBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert plusBtn != null : "fx:id=\"plusBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert resultTF != null : "fx:id=\"resultTF\" was not injected: check your FXML file 'calculator.fxml'.";
        assert sevenBtn != null : "fx:id=\"sevenBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert sixBtn != null : "fx:id=\"sixBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert threeBtn != null : "fx:id=\"threeBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert twoBtn != null : "fx:id=\"twoBtn\" was not injected: check your FXML file 'calculator.fxml'.";
        assert zeroBtn != null : "fx:id=\"zeroBtn\" was not injected: check your FXML file 'calculator.fxml'.";

    }

}
