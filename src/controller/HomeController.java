package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.AppRunningManager;
import model.PatientInfo;
import model.Response;

public class HomeController {
	@FXML
	private ImageView backImage;
	
    @FXML
	private ImageView headImage;

    @FXML
    private AnchorPane homePane;
    
    @FXML
    private ImageView tipImage2;

    @FXML
    private ImageView tipImage1;

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane patientListPane;
    
    @FXML
    private Label tipLabel;
    
    @FXML
    private JFXButton addButton;

    @FXML
    private VBox patientListBox;

    @FXML
    void goAddPatient(ActionEvent event) throws Exception{
    	AppRunningManager.loadPopUpScene("/view/NewPatient", 450, 500, homePane);
    }
    
    public void setPatientList(ArrayList<PatientInfo> patientList) throws IOException {
    	for(PatientInfo eachPatient: patientList) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatientProfile.fxml"));
			patientListBox.getChildren().add(loader.load());
			PatientProfileController controller = loader.getController();
			controller.initPatientInfo(eachPatient);
			controller.initStyle();
		}
    	patientListPane.setContent(patientListBox);
    }
    
    public void setTipInfo(boolean flag) {
    	tipImage1.setVisible(flag);
		tipImage2.setVisible(flag);
		tipLabel.setVisible(flag);
    }
    public void initialize() throws Exception{
    	headImage.setImage(new Image("image/sun-shine-head.jpg"));
    	tipImage1.setImage(new Image("image/boy.png"));
    	tipImage2.setImage(new Image("image/girl.png"));
    	backImage.setImage(new Image("image/back.png"));
    	patientListBox.setSpacing(20);
    	if(AppRunningManager.processQuery("Get Patient List", new String[] {AppRunningManager.getCurrentTherapist().getId()})) {
    		if(AppRunningManager.getCurrentPatientList().size() > 0) {
    			setPatientList(AppRunningManager.getCurrentPatientList());
    			setTipInfo(false);
    		}
    		else {
    			setTipInfo(true);
    		}
    	}
    	
    	searchBar.textProperty().addListener(new ChangeListener<String>() {
    		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			patientListBox.getChildren().clear();
    			ArrayList<PatientInfo> foundPatient = AppRunningManager.doLocalSearch(searchBar.getText());
    			if(foundPatient.size() > 0) {
    				try {
    					setPatientList(foundPatient);
    				}catch(Exception error) {}
    				setTipInfo(false);
    			}
    			else {
    				setTipInfo(true);
    			}
    		}   		
    	});
    	backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    		 @Override
    	     public void handle(MouseEvent event) {
    			try {
    				AppRunningManager.loadScene("/view/Login", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    			}catch(Exception error) {}
           }
     	}); 
    	   	
    }
}
