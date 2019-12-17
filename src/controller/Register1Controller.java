package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.AppRunningManager;

public class Register1Controller {

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXTextField idTextField;

    @FXML
    private Label tipLabel;

    @FXML
    private JFXButton backButton;

    @FXML
    void goRegister2(ActionEvent event) throws Exception{
    	if(AppRunningManager.processQuery("Register1", new String[] {idTextField.getText()})) {
    		AppRunningManager.loadScene("/view/Register2", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    	}
    	else {
    		tipLabel.setText("ID NOT FOUND OR REGISTERD");
    	}
    }

    @FXML
    void doBack(ActionEvent event) throws Exception{
    	AppRunningManager.loadScene("/view/Login", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    }
    
    public void initialize() {
    	tipLabel.setText("");
    }

}

