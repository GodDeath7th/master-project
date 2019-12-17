package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameVBarController {
	private GameListController parent;
    @FXML
    private Label gameName;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView gameImage;
    
    public void initData(String game, GameListController parentController) {
    	parent = parentController;
    	gameName.setText(game.toUpperCase());
    	gameName.setAlignment(Pos.BASELINE_CENTER);
    	gameImage.setImage(new Image("image/"+game+".jpg"));
    	
    	rootPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     		 @Override
     	     public void handle(MouseEvent event) {
     			try {
     				parent.setSelectedGame(getThisController());
     			}catch(Exception error) {}
            }
      	}); 
    }
    
    public GameVBarController getThisController() {return this;}
    public AnchorPane getRootPane() {return rootPane;}
    public Label getGameName() {return gameName;}
    public String getGame() {return gameName.getText().toLowerCase();}
}
