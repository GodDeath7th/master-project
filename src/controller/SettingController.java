package controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.AppRunningManager;

public class SettingController {

    @FXML
    private Rectangle repeat4;

    @FXML
    private Rectangle repeat3;

    @FXML
    private Label game;

    @FXML
    private Rectangle repeat6;

    @FXML
    private Rectangle repeat5;

    @FXML
    private Rectangle repeat8;

    @FXML
    private Rectangle repeat7;

    @FXML
    private Rectangle repeat9;

    @FXML
    private Rectangle level10;

    @FXML
    private Rectangle repeat10;

    @FXML
    private Rectangle repeat2;

    @FXML
    private Rectangle repeat1;

    @FXML
    private Label levelLabel;

    @FXML
    private Label repeat;

    @FXML
    private JFXButton backButton;

    @FXML
    private Rectangle level5;

    @FXML
    private Rectangle level4;

    @FXML
    private Rectangle level7;

    @FXML
    private Rectangle level6;

    @FXML
    private Rectangle level9;

    @FXML
    private JFXButton startButton;

    @FXML
    private Label level;

    @FXML
    private Rectangle level8;

    @FXML
    private ImageView gameImage;

    @FXML
    private Label repeatLabel;

    @FXML
    private Rectangle level1;

    @FXML
    private Rectangle level3;

    @FXML
    private Rectangle level2;

    @FXML
    void doStart(ActionEvent event) throws Exception {
    	AppRunningManager.getCurrentGame().setType(game.getText().toLowerCase());
    	AppRunningManager.getCurrentGame().setDifficulty(level.getText());
    	AppRunningManager.getCurrentGame().setRepetition(repeat.getText());
    	AppRunningManager.jumpToGame();
    }

    @FXML
    void goBack(ActionEvent event) throws Exception{
    	AppRunningManager.loadScene("/view/PatientDetail", AppRunningManager.fullscreen_weight, AppRunningManager.fullscreen_height);
    }
    
    public void setLevelBarStyle(int i) {
    	for(int j=1; j<=i; j++) {
    		setLevelColor(j);
    	}
    	for(int j=i+1; j<=10; j++) {
    		removeLevelColor(j);
    	}
    	switch(i) {
    	case 1:case 2:
    		level.setTextFill(Color.LIGHTGREEN);
    		break;
		case 3:case 4:
			level.setTextFill(Color.GREEN);
			break;
		case 5:case 6:
			level.setTextFill(Color.YELLOW);
			break;
		case 7:case 8:
			level.setTextFill(Color.ORANGE);
			break;
		case 9:case 10:
			level.setTextFill(Color.RED);
			break;
    	}
    	
    	level.setText(String.valueOf(i));
    	AppRunningManager.getCurrentGame().setDifficulty(String.valueOf(i));
    }
    
    public void setLevelColor(int i) {
    	switch(i) {
    	case 1:
    		level1.setFill(Color.LIGHTGREEN);
    		break;
    	case 2:
    		level2.setFill(Color.LIGHTGREEN);
    		break;
    	case 3:
    		level3.setFill(Color.GREEN);
    		break;
    	case 4:
    		level4.setFill(Color.GREEN);
    		break;
    	case 5:
    		level5.setFill(Color.YELLOW);
    		break;
    	case 6:
    		level6.setFill(Color.YELLOW);
    		break;
    	case 7:
    		level7.setFill(Color.ORANGE);
    		break;
    	case 8:
    		level8.setFill(Color.ORANGE);
    		break;
    	case 9:
    		level9.setFill(Color.RED);
    		break;
    	case 10:
    		level10.setFill(Color.RED);
    		break;
    	default:
    		break;
    	}
    }
    
    public void removeLevelColor(int i) {
    	switch(i) {
    	case 1:
    		level1.setFill(Color.DARKGRAY);
    		break;
    	case 2:
    		level2.setFill(Color.DARKGRAY);
    		break;
    	case 3:
    		level3.setFill(Color.DARKGRAY);
    		break;
    	case 4:
    		level4.setFill(Color.DARKGRAY);
    		break;
    	case 5:
    		level5.setFill(Color.DARKGRAY);
    		break;
    	case 6:
    		level6.setFill(Color.DARKGRAY);
    		break;
    	case 7:
    		level7.setFill(Color.DARKGRAY);
    		break;
    	case 8:
    		level8.setFill(Color.DARKGRAY);
    		break;
    	case 9:
    		level9.setFill(Color.DARKGRAY);
    		break;
    	case 10:
    		level10.setFill(Color.DARKGRAY);
    		break;
    	default:
    		break;
    	}
    }
    public void setRepeatBarStyle(int i) {
    	for(int j=1; j<=i; j++) {
    		setRepeatColor(j, true);
    	}
    	for(int j=i+1; j<=10; j++) {
    		setRepeatColor(j, false);
    	}
    	repeat.setText(String.valueOf(i));
    	AppRunningManager.getCurrentGame().setRepetition(String.valueOf(i));
    }
    
    public void setRepeatColor(int i, boolean isSelected) {
    	Color fillColor;
    	if(isSelected) 
    		fillColor = Color.LIGHTGREEN;
    	else
    		fillColor = Color.DARKGREY;
    	switch(i) {
    	case 1:
    		repeat1.setFill(fillColor);
    		break;
    	case 2:
    		repeat2.setFill(fillColor);
    		break;
    	case 3:
    		repeat3.setFill(fillColor);
    		break;
    	case 4:
    		repeat4.setFill(fillColor);
    		break;
    	case 5:
    		repeat5.setFill(fillColor);
    		break;
    	case 6:
    		repeat6.setFill(fillColor);
    		break;
    	case 7:
    		repeat7.setFill(fillColor);
    		break;
    	case 8:
    		repeat8.setFill(fillColor);
    		break;
    	case 9:
    		repeat9.setFill(fillColor);
    		break;
    	case 10:
    		repeat10.setFill(fillColor);
    		break;
    	default:
    		break;
    	}
    }
    
    public void initialize() {
    	game.setText(AppRunningManager.getCurrentGame().getType().toUpperCase());
    	gameImage.setImage(new Image("image/"+AppRunningManager.getCurrentGame().getType()+".png"));
    	

    	repeat1.setFill(Color.LIGHTGREEN);
        repeat.setTextFill(Color.LIGHTGREEN);
        repeat.setText("1");
        level1.setFill(Color.LIGHTGREEN);
        level.setTextFill(Color.LIGHTGREEN);
        level.setText("1");
    	level1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    		 @Override
    	     public void handle(MouseEvent event) {
    			try {
    				setLevelBarStyle(1);
    			}catch(Exception error) {}
           }
     	}); 
    	level2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(2);
   			}catch(Exception error) {}
          }
    	}); 
    	level3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(3);
   			}catch(Exception error) {}
          }
    	}); 
    	level4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(4);
   			}catch(Exception error) {}
          }
    	}); 
    	level5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(5);
   			}catch(Exception error) {}
          }
    	}); 
    	level6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(6);
   			}catch(Exception error) {}
          }
    	}); 
    	level7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(7);
   			}catch(Exception error) {}
          }
    	}); 
    	level8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(8);
   			}catch(Exception error) {}
          }
    	}); 
    	level9.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(9);
   			}catch(Exception error) {}
          }
    	}); 
    	level10.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setLevelBarStyle(10);
   			}catch(Exception error) {}
          }
    	}); 
    	repeat1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   		 @Override
   	     public void handle(MouseEvent event) {
   			try {
   				setRepeatBarStyle(1);
   			}catch(Exception error) {}
          }
    	}); 
    	repeat2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(2);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(3);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(4);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(5);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(6);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(7);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(8);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat9.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(9);
      			}catch(Exception error) {}
             }
       	}); 
    	repeat10.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      		 @Override
      	     public void handle(MouseEvent event) {
      			try {
      				setRepeatBarStyle(10);
      			}catch(Exception error) {}
             }
       	}); 
    }
}