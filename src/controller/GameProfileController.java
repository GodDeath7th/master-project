package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Game;

public class GameProfileController {
	public Game game = new Game();
    @FXML
    private Label level;

    @FXML
    private Label repeat;

    @FXML
    private Rectangle repeatBar;

    @FXML
    private ImageView gameProfileImage;

    @FXML
    private Rectangle levelBar;

    @FXML
    private AnchorPane gameProfilePane;
    
    public void setLevelBarStyle(int levelLength) {
    	if(levelLength <= 2) {
    		level.setTextFill(Color.LIGHTGREEN);
    		levelBar.setFill(Color.LIGHTGREEN);
    	}
    	else if(levelLength <= 4) {
    		level.setTextFill(Color.GREEN);
    		levelBar.setFill(Color.GREEN);
    	}
    	else if(levelLength <= 6) {
    		level.setTextFill(Color.YELLOW);
    		levelBar.setFill(Color.YELLOW);
    	}
    	else if(levelLength <= 8) {
    		level.setTextFill(Color.ORANGE);
    		levelBar.setFill(Color.ORANGE);
    	}
    	else if(levelLength <= 10) {
    		level.setTextFill(Color.RED);
    		levelBar.setFill(Color.RED);
    	}
    	
    	level.setText(String.valueOf(levelLength));
    }
    
    public void setRepeatBarStyle(int repeatLength) {
    	repeatBar.setFill(Color.LIGHTGREEN);
    	repeat.setTextFill(Color.LIGHTGREEN);
    	repeat.setText(String.valueOf(repeatLength));
    }
    
    public void initDate(String[] content) {
    	game.setType(content[0]);
    	game.setDifficulty(content[1]);
    	game.setRepetition(content[2]);
    	gameProfileImage.setImage(new Image("image/"+content[0]+".png"));
    	levelBar.setWidth(140*Double.parseDouble(content[1])/(double)10);
    	repeatBar.setWidth(140*Double.parseDouble(content[2])/(double)10);
    	setLevelBarStyle(Integer.parseInt(content[1]));
    	setRepeatBarStyle(Integer.parseInt(content[2]));
    }
    
    public Game getGame() {return game;}
}
