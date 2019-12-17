package controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.AppRunningManager;
import model.Game;

public class GameHBarController {
	private NewPlanController parent;
	private Game game;
	
	@FXML
	private Label time;
	
    @FXML
    private Rectangle repeat4;

    @FXML
    private Rectangle repeat3;

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
    private Label repeat;

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
    private Rectangle level8;

    @FXML
    private Label level;

    @FXML
    private JFXButton stateButton;

    @FXML
    private AnchorPane gameHPane;

    @FXML
    private ImageView gameImage;

    @FXML
    private Rectangle level1;

    @FXML
    private Rectangle level3;

    @FXML
    private Rectangle level2;
    

    @FXML
    private Label levelLabel;
    

    @FXML
    private Label repeatLabel;

    @FXML
    
    // check the state of button, if remove, delete this piece of game in plan, if add, add it, if start, start game customized by this piece of game content
    public void doStateChange(ActionEvent event) throws Exception{
    	if(stateButton.getText().equals("ADD")) {
    		parent.addChosenGame(false, game.getType());
    	}
    	else if(stateButton.getText().equals("START"))
    	{
    		AppRunningManager.setCurrentGame(game);
    		AppRunningManager.jumpToGame();
    	}
    	else {
    		parent.removeChosenGame(this);
    	}
    }
    
    // set style for each segment of bar when being clicked
    public void setBarVisible(boolean flag, int i) {
    	for(Node node: gameHPane.getChildren()) {
    		if(node.getId().equals("repeat"+i)) {
    			node.setVisible(false);
    		}
    		else if(node.getId().equals("level"+i)) {
    			node.setVisible(false);
    		}
    	}
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
    	game.setDifficulty(String.valueOf(i));
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
    	game.setRepetition(String.valueOf(i));
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
    
    public void initRecentGameList(String gameName, String difficulty, String repetition, String timeStr)
    {	
    	// if this scene is initialized to graphically display recent played game, following code point detailed process
    	time.setVisible(true);
    	time.setText(timeStr);
    	// get recent game information and store it
    		game=new Game();
    		game.setType(gameName);
    		game.setDifficulty(difficulty);
    		game.setRepetition(repetition);
    		gameImage.setImage(new Image("image/"+gameName+".png"));
		gameHPane.setStyle("-fx-background-color:#bc51ff; "
				+"-fx-background-radius: 15; "
				+ "-fx-border-color:  #bd18ff; "
				+ "-fx-border-radius: 15;");
		stateButton.setText("START");
		
		// set style of level bar and repeat bar by invoking function about style mentioned above
		int difficultyCounter=0;
		while(difficultyCounter<Integer.parseInt(difficulty))
		{
			difficultyCounter++;
			setLevelColor(difficultyCounter);
			
		}
		
		int repetitionCounter=0;
		while(repetitionCounter<Integer.parseInt(repetition))
		{
			repetitionCounter++;
			setRepeatColor(repetitionCounter, true);
	
		}

		repeat.setTextFill(Color.LIGHTGREEN);
		repeat.setText(""+repetition);
		level.setTextFill(Color.LIGHTGREEN);
		level.setText(""+difficulty);
    }
    
    public void initData(NewPlanController parent, boolean flag, String gameName) {
    	
    	// set initial style of bar and bind event with each of them
    	
    	time.setVisible(false);
    	this.parent = parent;
    	game = new Game();
    	game.setType(gameName);
    	
    	time.setText("");
    	game.setDifficulty("1");
    	game.setRepetition("1");
    	
    	if(flag) {
    		gameImage.setImage(new Image("image/"+gameName+".png"));
    		gameHPane.setStyle("-fx-background-color: darkgrey; "
    				+"-fx-background-radius: 15; "
    				+ "-fx-border-color: grey; "
    				+ "-fx-border-radius: 15;");
    		level.setVisible(false);
    		repeat.setVisible(false);
    		levelLabel.setVisible(false);
    		repeatLabel.setVisible(false);
    		
    		for(int i=1; i<= 10; i++) {
    		setBarVisible(false, i);
    		}
    	}
    	else {
    		gameImage.setImage(new Image("image/"+gameName+".png"));
    		gameHPane.setStyle("-fx-background-color:#bc51ff; "
    				+"-fx-background-radius: 15; "
    				+ "-fx-border-color:  #bd18ff; "
    				+ "-fx-border-radius: 15;");
    		stateButton.setText("REMOVE");
    		repeat1.setFill(Color.LIGHTGREEN);
        	repeat.setTextFill(Color.LIGHTGREEN);
        	repeat.setText("1");
        	level1.setFill(Color.LIGHTGREEN);
        	level.setTextFill(Color.LIGHTGREEN);
        	level.setText("1");
    	}
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
    
    public AnchorPane getRootPane() {return gameHPane;}
    public Game getGame() {return game;}
}