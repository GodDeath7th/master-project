package controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.text.Text;
import model.AppRunningManager;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Circle;

public class GameTutorialController extends GameController {
	@FXML
	private AnchorPane GamePane;
	@FXML
	private Circle Game_Target;
	@FXML
	private Text Game_Text;
	@FXML
	private Button Tute_exit_btn;
	
	private int counter=0;
	private int score=0;
	private double x;
	private double y;
	

	public void initialize()
	{
		//Game_Target.setVisible(false);
		setType(AppRunningManager.getCurrentGame().getType());
		setDifficulty(AppRunningManager.getCurrentGame().getDifficulty());
		setRepetition("2");
		initializeEventHandler();
		randomCreateTargets();
		
	}
	
	public void initializeEventHandler()
	{
		EventHandler<MouseEvent> eventHandler=new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				if ((arg0.getX()>=(x-50.0))&&(arg0.getX()<=(x+50.0))&&(arg0.getY()>=(y-50.0))&&(arg0.getY()<=(y+50.0))) {
					counter++;
						try {
							tutegoodtry();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}
				else {
					try {
						tutebadtry();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				randomCreateTargets();
				if (counter>=2) {
					GamePane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
					try {
						finishtute1();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		GamePane.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	public void finishtute1() throws Exception{
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("RESULT");
		alert.setContentText("Congratulations! You have finish the tutorial! "+AppRunningManager.getHighestScore());
		
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result=alert.showAndWait();
		if (result.get()==ButtonType.OK) {
			try {
				AppRunningManager.loadScene("/view/Game", 1280, 720);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void randomCreateTargets() {
		// TODO Auto-generated method stub
		x=Math.random()*800;
		Game_Target.setLayoutX(x);
		y=Math.random()*450;
		Game_Target.setLayoutY(y);
		Game_Target.setRadius(30.0);
		
		Group group=new Group();
		group.getChildren().add(Game_Target);
	
		GamePane.getChildren().addAll(group);
		
	}
	
	public void tutegoodtry() throws Exception{
		Game_Text.setText("Good job!");
	}
	
	public void tutebadtry() throws Exception{
		Game_Text.setText("You're almost there!");
	}
	
	
	public void ActionOnTute_exit_btn(ActionEvent event) throws Exception{
		AppRunningManager.loadScene("/view/Game", 1280, 720);
	}
	
	
	
	

}
