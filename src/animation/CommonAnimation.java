package animation;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CommonAnimation {
	public static Rectangle mask = new Rectangle();
	public static void setMask(boolean flag, double startX, double startY, AnchorPane pane) {
		if(flag) {
			mask.setWidth(pane.getPrefWidth()-startX);
			mask.setHeight(pane.getPrefHeight()-startY);
			mask.setFill(Color.BLACK);
			mask.setId("mask");
			
			mask.setLayoutX(startX);
			mask.setLayoutY(startY);
			pane.getChildren().add(mask);
			FadeTransition ft = new FadeTransition();
			ft.setDuration(Duration.millis(500));
			ft.setNode(mask);
			ft.setFromValue(0);
			ft.setToValue(0.5);
			ft.play();
		}
		else {
			pane.getChildren().remove(mask);
		}
	}
	public static void slip(AnchorPane pane, double endX) {
		TranslateTransition openNav=new TranslateTransition(new Duration(1000), pane);
        openNav.setToX(endX);
        openNav.play();
	}
}
