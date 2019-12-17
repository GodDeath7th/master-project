package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.AppRunningManager;
import model.TherapistInfo;

public class Register2Controller {

    @FXML
    private JFXTextField secretTextField;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private Label tipLabel;

    @FXML
    private JFXTextField resecretTextField;

    @FXML
    private JFXButton confirmButton;

    @FXML
    void doRegister(ActionEvent event) throws Exception{
    	if(secretTextField.getText().equals(resecretTextField.getText())) {
    		if(AppRunningManager.processQuery("Register2", new String[] {AppRunningManager.getId(),nameTextField.getText(),secretTextField.getText()})){
    			AppRunningManager.loadScene("/view/Home", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    		}
    		else {
    			tipLabel.setText("REGISTER FAILER! PLEASE TRY LATER");
    		}
    	}
    	else {
    		tipLabel.setText("RESECRET DISMATCH SECRET!");
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
