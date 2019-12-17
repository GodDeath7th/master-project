package model;

import java.util.Optional;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class AbstractGames {
	private String type;
	private String difficulty;
	private String repetition;
	private int score;
	private int counter;
	
	
	public void setType(String type) {this.type = type;}
	public String getType() {return type;}
	
	public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
	public String getDifficulty() {return difficulty;}	
	
	public String getRepetition() {return repetition;}
	public void setRepetition(String repetition) {this.repetition = repetition;}
	
	public void storeGameRecord(int score){System.out.println("storing record: "+score);}
	public void getHighestRecord(){System.out.println("getting highest record");}
	
	public void pause() throws Exception 
	{
		
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("PAUSE");
		alert.setContentText("The Game Is Paused!");
		
		alert.getButtonTypes().setAll(ButtonType.CANCEL,ButtonType.FINISH);
		
		Optional<ButtonType> result=alert.showAndWait();
		if (result.get()==ButtonType.CANCEL) {
			alert.close();
		}
		else if(result.get()==ButtonType.FINISH){
			exitGame();
		}
		
	}
	public void skip() throws Exception {
		int index=AppRunningManager.getCurrentGameIndex();
		Game game;
		
		AppRunningManager.setCurrentGameIndex(index+1);
		game=AppRunningManager.getCurrentGameList().get(index+1);
		AppRunningManager.setCurrentGame(game);
		AppRunningManager.jumpToGame();
		
	};
	public void exitGame() throws Exception 
	{
		AppRunningManager.loadScene("/view/PatientDetail", 1280, 720);
	};
	
	public void tutorial() throws Exception {
		AppRunningManager.loadScene("/view/GameTutorial", 1280, 720);
	}
	
	public void finish(int score) {
		AppRunningManager.processQuery("Add Record", new String[]{Generator.generateId("Record"),AppRunningManager.getCurrentPatient().getId(),getType(),
				getDifficulty(),getRepetition(),String.valueOf(score), Generator.generateDate()});			
		}
		
}
