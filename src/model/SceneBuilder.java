package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

// this is the realizing function that can load graphic components for each window
public class SceneBuilder {
	public Scene build(String fxml, double width, double height) throws Exception{
		String fileName = fxml+".fxml";
		Parent parent = FXMLLoader.load(getClass().getResource(fileName));
		Scene scene = new Scene(parent, width, height);
		scene.setFill(Color.TRANSPARENT);
		return scene;
	}
}
