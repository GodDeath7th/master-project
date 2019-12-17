package controller;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import jdk.nashorn.internal.runtime.events.RecompilationEvent;
import model.AppRunningManager;
import model.Game;
import model.Plan;

public class GameListController {
	// indicate whether at least one game is selected;
	
	private boolean isOneSelected = false;
	private AnchorPane parent;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button currentButton;

    @FXML
    private HBox gameListBox;
    
    @FXML
    private VBox recentGameList;

    @FXML
    private JFXButton chooseButton;

    @FXML
    private Button caseButton;

    @FXML
    private AnchorPane gameListPane;

    @FXML
    // go to setting after choose one game
    void goSetting(ActionEvent event) throws Exception{
    	if(isOneSelected) {
    		AppRunningManager.isPlan = false;
    		AppRunningManager.setRootPage("GameList");
    		AppRunningManager.loadScene("/view/Setting", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    	}
    }

    @FXML
    // to view available game
    void goCurrent(ActionEvent event) throws Exception{
    	currentButton.setStyle("-fx-background-color: #51a8f5;"+
                               "-fx-background-radius: 10 0 0 10;"+
    			               "-fx-border-radius: 10 0 0 10;"+
                               "-fx-border-color: #51a8f5");
    	caseButton.setStyle("-fx-background-color: white;"+
                            "-fx-background-radius: 0 10 10 0;"+
	                        "-fx-border-radius: 0 10 10 0;"+
                            "-fx-border-color: #51a8f5");
    	currentButton.setTextFill(Color.WHITE);
    	caseButton.setTextFill(Paint.valueOf("#51a8f5"));
    	setRootContent("GameList");               
    }
    
    // to view recent played game
    @FXML
    void goCase(ActionEvent event)  throws Exception{
    	currentButton.setStyle("-fx-background-color: white;"+
                               "-fx-background-radius: 10 0 0 10;"+
	                           "-fx-border-radius: 10 0 0 10;"+
                               "-fx-border-color: #51a8f5");
        caseButton.setStyle("-fx-background-color: #51a8f5;"+
                            "-fx-background-radius: 0 10 10 0;"+
                            "-fx-border-radius: 0 10 10 0;"+
                            "-fx-border-color: #51a8f5");
        currentButton.setTextFill(Paint.valueOf("#51a8f5"));
        caseButton.setTextFill(Color.WHITE);
        setRootContent("Recent");
    }
    
    // set displayed component based on indication of recent game or game list
    public void setRootContent(String content) throws Exception {
    	//parent.getChildren().clear();
    	if(content.equals("GameList")) {
    		gameListBox.getChildren().clear();
    		FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("/view/GameVBar.fxml"));
        	FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/view/GameVBar.fxml"));
        	GameVBarController controller;
        	gameListBox.setSpacing(75);
        	
        	gameListBox.getChildren().add(firstLoader.load());
        	controller = firstLoader.getController();
        	controller.initData("soccer", this);
        	
        	gameListBox.getChildren().add(secondLoader.load());
        	controller = secondLoader.getController();
        	controller.initData("basketball", this);
        	
        	gameListBox.setVisible(true);
        	recentGameList.setVisible(false);
        //	parent.getChildren().add(rootPane);
    	}
    	else {
    		// get Recent 5 game
    	  	recentGameList.getChildren().clear();
    		if(AppRunningManager.processQuery("Get Recent Game List", new String[]{AppRunningManager.getCurrentPatient().getId()})) {
    			ArrayList<FXMLLoader> loaders=new ArrayList<FXMLLoader>();
    			if(AppRunningManager.getRecordedGameList().size()>0) {
    				int size=AppRunningManager.getRecordedGameList().size();
    				int loaderSize=5;
    				if(size<5) {
    					loaderSize=size;
    				}
        		for(int i=0; i<loaderSize;i++)
        		{
        			FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/GameHBar.fxml"));
        			loaders.add(loader);
        		}
        		
        		GameHBarController gameHBarController;
        		
        		recentGameList.setSpacing(30);
        		
        		int i=0;
        		int j=size-1;
        		
        		while(i<loaderSize)
        		{
        			recentGameList.getChildren().add(loaders.get(i).load());
            		gameHBarController=loaders.get(i).getController();
            		gameHBarController.initRecentGameList(AppRunningManager.getRecordedGameList().get(j).getType(), 
            				AppRunningManager.getRecordedGameList().get(j).getDifficulty(),
            				AppRunningManager.getRecordedGameList().get(j).getRepetition(),
            				AppRunningManager.getRecordedGameList().get(j).getTime());
            		i++;
            		j--;
        		}
    			}
    		}
    		gameListBox.setVisible(false);
        	recentGameList.setVisible(true);
    	}
    }
    
    // set style of game that is selected 
    public void setSelectedGame(GameVBarController children) {
    	for(Node node: gameListBox.getChildren()) {
    		if(node.equals(children.getRootPane())) {
    			//children.getGameName().setTextFill(Color.WHITE);
    			node.setStyle("-fx-background-color: lightblue;"+
    						  "-fx-background-radius: 15;"+
    		                  "-fx-border-radius: 15;"+
    					      "-fx-border-color:#408cff;");
    			isOneSelected = true;
    			AppRunningManager.getCurrentGame().setType(children.getGame());
    		}
    		else {
    			//children.getGameName().setTextFill(Color.BLACK);
    			node.setStyle( "-fx-background-color: white;"+
    						   "-fx-background-radius: 15;"+
		                       "-fx-border-radius: 15;"+
					           "-fx-border-color:black;");
    		}
    	}
    }
    
    public void initData(boolean isInCurrentPage, AnchorPane parentPane) throws Exception{
    	if(AppRunningManager.getCurrentGame() == null) 
    		AppRunningManager.setCurrentGame(new Game());

    	
    	parent = parentPane;
    	
    	if(isInCurrentPage) {
    		goCurrent(new ActionEvent());
    	}
    	else {
    		goCase(new ActionEvent());
    	}
    		
    }

}
