package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.AppRunningManager;
import model.Generator;
import model.Response;

public class NewPatientController {
	
    @FXML
    private ImageView title;
    
    @FXML
    private ImageView boyImg;
    
    @FXML
    private ImageView girlImg;
    
    @FXML
    private Label warningLabel;
    
    @FXML
    private JFXRadioButton maleOption;

    @FXML
    private AnchorPane NewPatientPane;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXRadioButton femaleOption;
    
    @FXML
    private TextField name;

    @FXML
    private JFXButton addButton;

    @FXML
    private ComboBox<String> ageBox;
    
    ToggleGroup genderGroup = new ToggleGroup();

    @FXML
    void doBack(ActionEvent event) {
    	AppRunningManager.closePopUpScene();
    }

    @FXML
    void addPatient(ActionEvent event) throws Exception{
    	
    	if (name.getText()==null || ageBox.getValue()==null) {
			warningLabel.setText("Please enter all the information");
			warningLabel.setVisible(true);
		}
    	else {
    	if(AppRunningManager.processQuery("Add Patient", new String[] {Generator.generateId("Patient"), AppRunningManager.getCurrentTherapist().getId(),
       		 name.getText(), genderGroup.getSelectedToggle().getUserData().toString(), ageBox.getValue()})) {
       		AppRunningManager.loadScene("/view/Home", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
       	}
       	AppRunningManager.closePopUpScene();
    	}
    }
    
    public void initialize() {
    	title.setImage(new Image("image/title.png"));
    	boyImg.setImage(new Image("image/boy.png"));
    	girlImg.setImage(new Image("image/girl.png"));
    	
    	warningLabel.setVisible(false);
    	maleOption.setToggleGroup(genderGroup);
    	maleOption.setUserData("male");
    	maleOption.setSelected(true);
    	femaleOption.setToggleGroup(genderGroup);
    	femaleOption.setUserData("female");
    	
    	ageBox.getItems().addAll(Generator.generateOption("Age"));
    }

}
