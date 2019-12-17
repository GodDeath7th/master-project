package animation;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import javafx.scene.shape.Line;
import javafx.util.Duration;

public class NodePathTransition{
	public ImageView indicator;
	public PathTransition trans = new PathTransition();
	public double layout_x;
	public double layout_y;
	
	public NodePathTransition() {
		trans.setCycleCount(1);
		trans.setOnFinished((ActionEvent event)-> {
			trans.play();
		});
	}
	
	public void transition_of_straight_line(ImageView indicator, double start_x, double start_y, double end_x, double end_y, Duration duration) {
		
		this.indicator = indicator;
		Line line = new Line(start_x, start_y, end_x, end_y);
		
		trans.setNode(indicator);
		trans.setPath(line);
		trans.setDuration(duration);
		
	}
	
	public void play(){
		trans.play();
	}
	
	public void recover() {
		trans.stop();
		indicator.setTranslateX(0);
		indicator.setTranslateY(0);
	}
	public void stop() {
		trans.setOnFinished(null);
	}
	
}
