package start;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppRunningManager;
import model.Game;
import model.SceneBuilder;

public class GameStart extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		AppRunningManager.isPlan = false;
		AppRunningManager.setCurrentGame(new Game("football", "1", "4"));
		Stage rootStage = primaryStage;		
		rootStage.initStyle(StageStyle.UNDECORATED);	
		Scene rootScene = new SceneBuilder().build("/view/SoccerGame", 1280, 720);
		rootStage.setScene(rootScene);
		rootStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
