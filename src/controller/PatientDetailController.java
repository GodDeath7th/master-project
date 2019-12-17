package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import animation.CommonAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.AppRunningManager;
import model.Generator;
import model.Record;

public class PatientDetailController {
	ToggleGroup group = new ToggleGroup();
	@FXML
    private ImageView maleImage;

    @FXML
    private ImageView femaleImage;

    @FXML
    private ComboBox<String> ageBox;

    @FXML
    private JFXRadioButton maleOption;
    
    @FXML
    private JFXRadioButton femaleOption;
    
    @FXML
    private TextField newName;
    
    @FXML
    private JFXButton gameButton;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Rectangle background;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label name;

    @FXML
    private ImageView genderImage;

    @FXML
    private JFXButton planButton;

    @FXML
    private AnchorPane slipPane;

    @FXML
    private JFXButton statisticButton;
    

    @FXML
    private ImageView backImage;
    
    private void setButtonStyle(boolean planSelected, boolean gameSelected, boolean statisticSelected) {
    	if(planSelected)
    		planButton.setStyle("-fx-background-color:#ffa13e; "+
    							"-fx-background-radius:15 0 0 0;");
    	else 
    		planButton.setStyle("-fx-background-color: lightgreen; "+
					"-fx-background-radius:15 0 0 0;");
    	if(gameSelected) 
    		gameButton.setStyle("-fx-background-color:#ffa13e; "+
					"-fx-background-radius:0 0 0 0;");
    	else 
    		gameButton.setStyle("-fx-background-color:lightgreen; "+
					"-fx-background-radius:0 0 0 0;");
    	if(statisticSelected)
    		statisticButton.setStyle("-fx-background-color:#ffa13e; "+
					"-fx-background-radius:0 15 0 0;");
    	else
    		statisticButton.setStyle("-fx-background-color:lightgreen;"+
					"-fx-background-radius:0 15 0 0;");
    }
    @FXML
    void goPlan(ActionEvent event) throws Exception{
    	setRootContent("PlanList");
    	setButtonStyle(true, false, false);
    }

    @FXML
    void goGame(ActionEvent event) throws Exception{
    	setRootContent("GameList");
    	setButtonStyle(false, true, false);
    }

    @FXML
    void goStatistic(ActionEvent event) throws Exception{
    	setRootContent("RecordChart");
    	setButtonStyle(false, false, true);
    }
    
    @FXML
    void doEdit(ActionEvent event) {
    	
    	String name=newName.getText();
    	String gender=group.getSelectedToggle().getUserData().toString();
    	String age=ageBox.getValue();
    	
    	if(name=="")
    	{
    		name=AppRunningManager.getCurrentPatient().getName();
    	}
    	if(age==null)
    	{
    		age=AppRunningManager.getCurrentPatient().getAge();
    	}
    		
    	
    	AppRunningManager.processQuery("Edit Patient Profile", new String[] {AppRunningManager.getCurrentPatient().getId(), AppRunningManager.getCurrentPatient().getTid(), 
    			name, gender, age});
    	setBackgroundStyle();
    }

    
    @FXML
    void goBack(ActionEvent event) {
    	CommonAnimation.setMask(false, 350, 0, homePane);
    	CommonAnimation.slip(slipPane, 0);
    	backImage.setVisible(true);
    }
    
    public void setBackgroundStyle(){
    	if(AppRunningManager.getCurrentPatient().getGender().equals("male")) {
    		genderImage.setImage(new Image("image/boy.png"));
    		background.setFill(Color.LIGHTBLUE);
    	}
    	else {
    		genderImage.setImage(new Image("image/girl.png"));
    		background.setFill(Color.PINK);
    	}
    	
    	name.setText(AppRunningManager.getCurrentPatient().getName());
    }
    public void setRootContent(String fxmlName) throws Exception{
    	rootPane.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/"+fxmlName+".fxml"));
		rootPane.getChildren().add(loader.load());
		if(fxmlName.equals("PlanList")) {
			PlanListController controller = loader.getController();
			controller.initData(rootPane);
		}
		else if(fxmlName.equals("GameList")) {
			GameListController controller = loader.getController();
			controller.initData(true, rootPane);
		}
		else if(fxmlName.equals("RecordChart")) {
			RecordChartController controller = loader.getController();
			controller.initData(rootPane);
		}
    }
    public void initialize() throws Exception {
    	setBackgroundStyle();
    	
    	maleImage.setImage(new Image("image/boy.png"));
    	femaleImage.setImage(new Image("image/girl.png"));
    	backImage.setImage(new Image("image/back.png"));
    	
    	maleOption.setToggleGroup(group);
    	maleOption.setSelected(true);
    	maleOption.setUserData("male");
    	
    	femaleOption.setToggleGroup(group);
    	femaleOption.setUserData("female");
    	
    	
    	ageBox.setItems(Generator.generateOption("Age"));
    	AppRunningManager.processQuery("Get Record List", new String[] {AppRunningManager.getCurrentPatient().getId()});
    	
    	if(AppRunningManager.getRootPage().equals(""))
    		setRootContent("PlanList");
    	else {
    		setRootContent(AppRunningManager.getRootPage());
    		AppRunningManager.setRootPage("");
    	}
		
    	genderImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			backImage.setVisible(false);
      			CommonAnimation.setMask(true, 350, 0, homePane);
      			CommonAnimation.slip(slipPane, slipPane.getPrefWidth());
             }
       	}); 
    	
    	backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     		 @Override
     	     public void handle(MouseEvent event) {
     			try {
     				AppRunningManager.loadScene("/view/Home", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
     			}catch(Exception error) {}
            }
      	}); 
    }

}

