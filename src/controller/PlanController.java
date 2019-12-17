package controller;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.AppRunningManager;
import model.Game;
import model.Plan;

public class PlanController {
	private PlanListController parentController;
	private ArrayList<GameProfileController> controllers = new ArrayList<>();
	private Plan thisPlan;

    @FXML
    private VBox planBox;

    @FXML
    private JFXButton startButton;

    @FXML
    private JFXButton removeButton;

    @FXML
    private AnchorPane planPane;


    @FXML
    void doRemove(ActionEvent event) {
    	System.out.println(thisPlan.getId());
    	AppRunningManager.processQuery("Remove Plan", new String[] {thisPlan.getId()});
    	parentController.removePlan(planPane);
    }

    @FXML
    void doStart(ActionEvent event) throws Exception{
    	ArrayList<Game> playGame = new ArrayList<>();
    	for(GameProfileController controller: controllers) {
    		playGame.add(controller.getGame());
    	}
    	AppRunningManager.isPlan = true;
    	AppRunningManager.setCurrentGameList(playGame);
    	AppRunningManager.setCurrentGameIndex(0);
    	AppRunningManager.jumpToGame();
    }
    
    public void initData(Plan plan, PlanListController thisController) throws Exception{
    	thisPlan = plan;
    	String[] content = thisPlan.getContent().split("/");
    	parentController = thisController;
    	planBox.setSpacing(5);
    	for(int i=0; i< content.length; i=i+3) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameProfile.fxml"));
    		planBox.getChildren().add(loader.load());
    		GameProfileController controller = loader.getController();
    		controller.initDate(new String[] {content[i], content[i+1], content[i+2]});
    		controllers.add(controller);
    	}
    }
}
