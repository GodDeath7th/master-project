package animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FontTransition extends Transition{
	public int start, end;
	public Text fontController;
	
	public void transition(Text fontController, int start, int end, Duration duration) {
		this.fontController = fontController;
		this.start = start;
		this.end = end - start;
		setCycleDuration(duration);  
	    setInterpolator(Interpolator.LINEAR); 
		
	}
	@Override
	public void interpolate(double frac) {
		int size = (int) ((end * frac) + start);
        if(size<=end) {      	
            fontController.setFont(Font.font(size));
        }
	}

}
