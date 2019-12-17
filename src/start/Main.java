package start;

import javafx.application.*;
import javafx.stage.Stage;
import model.AppRunningManager;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		AppRunningManager.start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
