package model;

import controller.SoccerGameController;
import javafx.scene.control.TextField;
// this class is used for count down, stop and recover time for soccer game
public class Timer extends Thread{
	private SoccerGameController soccerGame;
	private TextField time = new TextField();
	private int countDown;
	private boolean needStop;
	private Thread t;
	public Timer(SoccerGameController controller, TextField label, int time) {
		soccerGame = controller;
		countDown = time;
		this.time = label;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				}catch (Exception error){}
			if(!needStop) {
				countDown--;
				time.setText(String.valueOf(countDown));
				if(countDown == 0) {
					soccerGame.doNext();
				}
			}
		}
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this, "");
		}
		t.start();
	}
	
	public void reset(int time) {
		countDown = time; 
	}
	
	public void stopTime() {
		needStop = true;
	}
	
	public void recover() {
		needStop = false;
	}
}
