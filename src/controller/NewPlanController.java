package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.AppRunningManager;
import model.Generator;
import model.Response;

public class NewPlanController {
	private AnchorPane parent;
	private int currentChosenGameNumber;
	private ArrayList<GameHBarController> controllers;
	@FXML
	private AnchorPane rootPane;
	
    @FXML
    private ScrollPane availableGamePane;

    @FXML
    private AnchorPane choseGamePane;

    @FXML
    private VBox chosenGameBox;

    @FXML
    private VBox availableGameBox;

    @FXML
    private JFXButton addButton;
        
    @FXML
    void doAdd(ActionEvent event) throws Exception{
    	if(AppRunningManager.processQuery("Add Plan", new String[] {Generator.generateId("Plan"), AppRunningManager.getCurrentPatient().getId(), getGameContent()})) {
    		parent.getChildren().clear();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlanList.fxml"));
    		parent.getChildren().add(loader.load());
    		PlanListController controller = loader.getController();
    		controller.initData(parent);
    	}
    }
    
    public String getGameContent() {
    	String content = "";
    	for(GameHBarController controller: controllers) {
    		content = content + controller.getGame().getType()+"/"+ controller.getGame().getDifficulty()+"/"+controller.getGame().getRepetition()+"/";
    	}
    	content.substring(0, content.length()-1);
    	return content;
    }
    
    public void addGame(String gameName) throws Exception {
    	setAvailableGame(false, gameName);
    }
    public void  setAvailableGame(boolean flag, String gameName) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameHBar.fxml"));
    	availableGameBox.getChildren().add(loader.load());
    	GameHBarController controller = loader.getController();
    	controller.initData(this, flag, gameName);
    }
    
    public void addChosenGame(boolean flag, String gameName) throws Exception{
    	if(currentChosenGameNumber < 5) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameHBar.fxml"));
    		chosenGameBox.getChildren().add(loader.load());
    		GameHBarController controller = loader.getController();
    		controller.initData(this, flag, gameName);
    		controllers.add(controller);
    		currentChosenGameNumber++;
    	}else {}
    }
    
    public void removeChosenGame(GameHBarController thisGame) {
    	for(Node node: chosenGameBox.getChildren()) {
    		if(node.equals(thisGame.getRootPane())) {
    			chosenGameBox.getChildren().remove(node);
    			controllers.remove(thisGame);
    			currentChosenGameNumber--;
    			break;		
    		}
    	}
    }
    
    public void initData(boolean flag, AnchorPane parentPane) throws Exception{
    	parent = parentPane;
    	controllers = new ArrayList<>();
    	
    	currentChosenGameNumber = 0;
    	chosenGameBox.setSpacing(10);
    	availableGameBox.setSpacing(20);
    	setAvailableGame(flag, "soccer");
    	setAvailableGame(flag, "basketball");
    }

}