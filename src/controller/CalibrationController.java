package controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.AppRunningManager;
import model.Game;

public class CalibrationController {
	//this is used for store number of count dot
	int clickedDot = 0;

    @FXML
    private Circle r2;

    @FXML
    private Circle r3;

    @FXML
    private Circle r4;

    @FXML
    private Text tipText;

    @FXML
    private Circle r1;
    
    @FXML
    private AnchorPane calibratePane;
    
    // count dot clicked, jump to game in 2s if all of 4 clicked
    public void countDot() {
    	clickedDot++;
    	if(clickedDot == 4) {
			tipText.setFill(Color.LIGHTGREEN);
			tipText.setText("FINISHED. GAME WILL START SOON IN 2 SECOND");
			PauseTransition pause = new PauseTransition(
   			        Duration.seconds(2)
   			    );
				
				// set 2s wait time before come to games
   			    pause.setOnFinished(Event -> {
   			    	try {
   			    		// for plan, load game subsequently and load scene of first game 
   			    		if(!AppRunningManager.isPlan) {
   			    			if(AppRunningManager.getCurrentGame().getType().equals("soccer")) {
   			    			AppRunningManager.loadScene("/view/SoccerGame", 1280, 720);
   			    			}
   			    			else if(AppRunningManager.getCurrentGame().getType().equals("basketball")) {
   			    				AppRunningManager.loadScene("/view/Game", 1280, 720);
   			    			}
   			    		}
   			    		// for single game, start directly to load scene of this game
   			    		
   			    		else {
   			    			if(AppRunningManager.getCurrentGameList().get(AppRunningManager.getCurrentGameIndex()).getType().equals("soccer")) {
       			    			AppRunningManager.loadScene("/view/SoccerGame", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
       			    		}
       			    		else if(AppRunningManager.getCurrentGameList().get(AppRunningManager.getCurrentGameIndex()).getType().equals("basketball")) {
       			    			AppRunningManager.loadScene("/view/Game", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
       			    		}
   			    			
   			    		}
   	       			
   	       			}catch(Exception e) {
   	       				
   	       			}
   			    });  
   			 pause.play();   
  		}		 
    }
    
    public void initialize() {
    	//set tip content
    	
    	tipText.setText("PLEASE CLICK 4 CIRCLE AT CORNER");
    	tipText.setTextAlignment(TextAlignment.CENTER);
    	
        
        // from r1 to r4, dot should be clicked and jump to game after 4 click
        r1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			 r1.setFill(Color.LIGHTGREEN);
      			 countDot();
      		 }
        });
        r2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     		 @Override
     	     public void handle(MouseEvent event) {
     			r2.setFill(Color.LIGHTGREEN);
     			countDot();
     		 }
        });
        r3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     		 @Override
     	     public void handle(MouseEvent event) {
     			r3.setFill(Color.LIGHTGREEN);
     			countDot();
     		 }
        });
        r4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     		 @Override
     	     public void handle(MouseEvent event) {
     			r4.setFill(Color.LIGHTGREEN);
     			countDot();
     		 }
        });
    }
}
      	        
  