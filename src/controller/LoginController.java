package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AppRunningManager;
import model.Response;

public class LoginController {
	@FXML
    private ImageView rootImage;
	
	@FXML
	private ImageView titleImage;
	
	@FXML
	private ImageView userImage;

	@FXML
	private ImageView passwordImage;
	
    @FXML
    private PasswordField password;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label tip;

    @FXML
    private TextField username;

    @FXML
    void doLogin(ActionEvent event) throws Exception{
    	if(AppRunningManager.processQuery("Login", new String[] {username.getText(), password.getText()})) {
    		AppRunningManager.loadScene("/view/Home", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    	}
    	else {
    		tip.setText("WRONG NAME OR SECRET!");
    	}
    }

    @FXML
    void goRegister1(ActionEvent event) throws Exception{   
    	AppRunningManager.loadScene("/view/Register1", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    }
    
    public void initialize() {
    	rootImage.setImage(new Image("image/sun-shine.jpg"));
    	titleImage.setImage(new Image("image/title.png"));
    	userImage.setImage(new Image("image/user.png"));
    	passwordImage.setImage(new Image("image/password.png"));
    	tip.setText("");
    }


}
