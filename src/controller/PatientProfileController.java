package controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.AppRunningManager;
import model.PatientInfo;

public class PatientProfileController {
	private PatientInfo thisPatient;
	
    @FXML
    private JFXButton detailButton;

    @FXML
    private Label profileLabel;
    
    @FXML
    private Label idLabel;

    @FXML
    private ImageView genderImage;

    @FXML
    private AnchorPane patientProfilePane;

    @FXML
    void goDetail(ActionEvent event) throws Exception{
    	AppRunningManager.setCurrentPatient(thisPatient);
    	AppRunningManager.loadScene("/view/PatientDetail", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    }
    
    public void initPatientInfo(PatientInfo patient) {
    	thisPatient = patient;
    	profileLabel.setText(patient.getName()+"  "+patient.getAge());
    	idLabel.setText(patient.getId().replaceAll("Patient", ""));
    }
    
    public void initStyle() {
    	if(thisPatient.getGender().equals("male")) {
    		genderImage.setImage(new Image("image/boy.png"));
    		patientProfilePane.setStyle("-fx-background-color: lightblue; "
    				                   +"-fx-background-radius: 10; "
    				                   + "-fx-border-color: #408cff; "
    				                   + "-fx-border-radius: 10;");
    	}
    	else {
    		genderImage.setImage(new Image("image/girl.png"));
    		patientProfilePane.setStyle("-fx-background-color: pink; "
	                   +"-fx-background-radius: 10; "
	                   + "-fx-border-color: #ff48c5; "
	                   + "-fx-border-radius: 10;");
    	}
    }
 
}