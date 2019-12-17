package controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.jfoenix.controls.JFXButton;

import animation.CommonAnimation;
import animation.FontTransition;
import animation.NodePathTransition;
import guide.GameGuide;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.AppRunningManager;
import model.Game;
import model.Generator;
import model.SceneBuilder;
import model.Timer;

public class SoccerGameController {
	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    
    // store times for play, count down time and playing game content
    private int count = 0;
    private int countDown;
    private Game thisGame = new Game();
    
    
    // store score and time when pause
    private String midScore;
    private String midTime;
    
    // is identified whether game really start
    private boolean isStarted = false;
    
    // timer for counting down for each play
    private Timer timer;
    
    @FXML
    private ImageView soccer1;
    
    @FXML
    private Circle target1;
    
    @FXML
    private ImageView scoreImg;
    
    @FXML
    private ImageView timeImg;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private JFXButton startButton;
    @FXML
    private ImageView ground;
    
    @FXML
    private Circle target;
    
    @FXML
    private ImageView guide;
    
    @FXML
    private ImageView dialog;
    
    @FXML
    private ImageView indicator;
    
    @FXML
    private ImageView indicator_of_step3;
	    
    @FXML
    private ImageView soccer;

    @FXML
    private JFXButton loginButton11;

    @FXML
    private JFXButton loginButton1;

    @FXML
    private JFXButton guideButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Rectangle gateBoundary;

    @FXML
    private Rectangle topPost;

    @FXML
    private Rectangle rightPost;

    @FXML
    private Rectangle leftPost;

    @FXML
    private Text guideText;
    
    @FXML
    private TextField time;
    
    @FXML
    private TextField score;
    

    @FXML
    private JFXButton pauseButton;

    @FXML
    private JFXButton quitButton;
    
    @FXML
    private JFXButton nextButton;
    private NodePathTransition trans = new NodePathTransition();
    
    // go next count play
    public void doNext(){
       	generateTarget();
       	count++;
       	countDown = 15 - Integer.parseInt(thisGame.getDifficulty());
      	timer.reset(countDown);
       	soccer.setTranslateX(0);
    	soccer.setTranslateY(0);
    }
    
    // leave game
    @FXML
    void doQuit(ActionEvent event) throws Exception{
    	AppRunningManager.setRootPage("RecordChart");
    	AppRunningManager.loadScene("/view/PatientDetail", 1280, 720);
    }

    //pause game
    @FXML
    void doPause(ActionEvent event) throws Exception{
    	if(pauseButton.getText().equals("PAUSE")){
    		soccer.setVisible(false);
    		soccer1.setVisible(true);
    		soccer1.relocate(soccer.getLayoutX()+soccer.getTranslateX(), soccer.getLayoutY()+soccer.getTranslateY());
    		pauseButton.setText("CONTINUE");
    		timer.stopTime();
    	}
    	else {
    		soccer.setVisible(true);
    		soccer1.setVisible(false);
    		pauseButton.setText("PAUSE");
    		timer.recover();
    	}
    }
    
    // go to next game
    @FXML
    void goNext(ActionEvent event) throws Exception{
    	AppRunningManager.setCurrentGameIndex(AppRunningManager.getCurrentGameIndex()+1);
    	AppRunningManager.jumpToGame();
    }
    
    // start the game
    @FXML
    void doStart(ActionEvent event){
    	count++;
    	//initialize timer
    	timer = new Timer(this, time, countDown);
    	timer.start();
    	isStarted = true;
    	startButton.setVisible(false);
    	
    	pauseButton.setVisible(true);
    	soccer.setVisible(true);
    	target.setVisible(true);
    	pauseButton.setText("PAUSE");
    }
    
    @FXML
    void goGuide(ActionEvent event) {
    	if(guideButton.getText().equals("HOW TO PLAY")) {
    		if(isStarted)
    			timer.stopTime();
    		else
    			midTime = time.getText();
    		midScore = score.getText();
    		startButton.setVisible(false);
    		guideButton.setText("NEXT");
    		time.setText("10");
    		score.setText("0");
    		guide.setVisible(true);
    		dialog.setVisible(true);
    		soccer.setVisible(false);
    		soccer1.setVisible(true);
        	target.setVisible(false);
        	target1.setVisible(true);
        	soccer1.relocate(soccer.getLayoutX(), soccer.getLayoutY());
    		goNext();
    	}
    	else if(guideButton.getText().equals("NEXT")) {
    		goNext();
    	}
    	else if(guideButton.getText().equals("GO PLAY")) {
    		if(!isStarted) {
    			startButton.setVisible(true);
    			time.setText("0");
        		score.setText("0");
    		}
    		else {
    			soccer.setVisible(true);
    			target.setVisible(true);
        		score.setText(midScore);
    		}

    		guide.setVisible(false);
    		dialog.setVisible(false);
    		guideButton.setText("HOW TO PLAY");
    		soccer1.setVisible(false);
    		target1.setVisible(false);
    		guideText.setText("");
    		if(isStarted)
    			time.setText(midTime);
    		if(pauseButton.getText().equals("PAUSE"))
    			timer.recover();
    	}
    			
    }

    public void goNext() {
    
    	
    	switch(AppRunningManager.soccer_guide_step) {
    	case 0:
    		guideText.setText(GameGuide.start_guide_soccer);
    		break;
    	case 1:
    		guideText.setText(GameGuide.step1_guide_soccer);
    		indicator.setVisible(true);
    		trans.transition_of_straight_line(indicator, indicator.getFitWidth()/2, indicator.getFitHeight()/2, indicator.getFitWidth()/2, indicator.getFitHeight()/2+20, Duration.millis(500));
    		trans.play();
    		break;
    	case 2:	
    		guideText.setText(GameGuide.step2_guide_soccer);
    		indicator.setVisible(false);
    		trans.recover();
    		trans.transition_of_straight_line(soccer1, soccer1.getFitWidth()/2, soccer1.getFitHeight()/2, target1.getLayoutX()-soccer1.getLayoutX(), target1.getLayoutY()-soccer1.getLayoutY(), Duration.millis(3000));
    		trans.play();
    		break;
    	case 3:	
    		guideText.setText(GameGuide.step3_guide_soccer);
    		indicator_of_step3.setVisible(true);
    		trans.recover();
    		trans.transition_of_straight_line(indicator_of_step3, indicator_of_step3.getFitWidth()/2, indicator_of_step3.getFitHeight()/2, indicator_of_step3.getFitWidth()/2, indicator_of_step3.getFitHeight()/2-20, Duration.millis(500));
    		trans.play();
    		break;
    	case 4:	
    		guideText.setText(GameGuide.step4_guide_soccer);
    		indicator_of_step3.setVisible(false);
    		trans.recover();
    		trans.stop();
    		break;
    	case 5:	
    		guideText.setText(GameGuide.end_guide_soccer);
    		guideButton.setText("GO PLAY");
    		break;
    	default:
    		break;
    	}
    	if(AppRunningManager.soccer_guide_step < 5)
    		AppRunningManager.soccer_guide_step++;
    	else
    		AppRunningManager.soccer_guide_step = 0;
    }
    
    public void generateTarget(){
    	double positionX;
    	double positionY;
    	Random random = new Random();
    	positionX = random.nextDouble()*560+350;
    	positionY = random.nextDouble()*120+190;
    	
    	target.setLayoutX(positionX);
    	target.setLayoutY(positionY);
    };
    
    public void initialize() throws Exception{
    	
    	pauseButton.setText("");
    	timeImg.setImage(new Image("image/time.png"));
    	scoreImg.setImage(new Image("image/score.png"));
    	guide.setImage(new Image("image/guide.png"));
    	dialog.setImage(new Image("image/thought-cloud-2-md.png"));
    	indicator.setImage(new Image("image/down-arrow.png"));
    	indicator_of_step3.setImage(new Image("image/up-arrow.png"));
    	
    	pauseButton.setVisible(false);
    	soccer.setVisible(false);
    	soccer1.setVisible(false);
    	target.setVisible(false);
    	target1.setVisible(false);
    	
    	if(AppRunningManager.isPlan)
    		thisGame = AppRunningManager.getCurrentGameList().get(AppRunningManager.getCurrentGameIndex());
    	else
    		thisGame = AppRunningManager.getCurrentGame();
    	
    	countDown = 15-Integer.parseInt(thisGame.getDifficulty());
    	
    	if(AppRunningManager.isPlan) {
    		if((AppRunningManager.getCurrentGameIndex()+1)== AppRunningManager.getCurrentGameList().size())
    			nextButton.setVisible(false);
    	}
    	else {
    		nextButton.setVisible(false);
    	}
    	ground.setImage(new Image("image/soccer_stadium.png"));
    	soccer.setImage(new Image("image/soccer.png"));
    	soccer1.setImage(new Image("image/soccer.png"));
    	
    	guideText.setText("");
    	guideText.setCache(true);
    	guideText.setCacheHint(CacheHint.QUALITY);
    	indicator.setVisible(false);
    	indicator.setCache(true);
    	indicator.setCacheHint(CacheHint.QUALITY);
    	indicator_of_step3.setVisible(false);
    	indicator_of_step3.setCache(true);
    	indicator_of_step3.setCacheHint(CacheHint.QUALITY);
    	guide.setVisible(false);
    	dialog.setVisible(false);
    	
    	score.setText("0");
    	time.setText("0");
        
        EventHandler<MouseEvent> mousePressedHandler = 
                new EventHandler<MouseEvent>() {
         
                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
                }
            };
             
        EventHandler<MouseEvent> mouseDraggedHandler = 
                new EventHandler<MouseEvent>() {
         
                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                     
                    ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                    ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
                    
                    double distanceX = ((ImageView)(t.getSource())).getTranslateX()+((ImageView)(t.getSource())).getLayoutX()+50-target.getLayoutX();
                    double distanceY = ((ImageView)(t.getSource())).getTranslateY()+((ImageView)(t.getSource())).getLayoutY()+50-target.getLayoutY();
                    if(distanceX*distanceX + distanceY*distanceY <= 3600) {
                    	target.setFill(Color.LIGHTGREEN);
                    }
                    else {
                    	target.setFill(Paint.valueOf("#fcff1f"));
                    }
                }
            };
         
            
         EventHandler<MouseEvent> mouseReleasedHandler = 
                new EventHandler<MouseEvent>() {
           
                    @Override
                    public void handle(MouseEvent t) {
                    	double distanceX = ((ImageView)(t.getSource())).getTranslateX()+((ImageView)(t.getSource())).getLayoutX()+50-target.getLayoutX();
                        double distanceY = ((ImageView)(t.getSource())).getTranslateY()+((ImageView)(t.getSource())).getLayoutY()+50-target.getLayoutY();
                        if(distanceX*distanceX + distanceY*distanceY <= 3600) {   
                        	 target.setFill(Paint.valueOf("#fcff1f"));
                        	 if(count < Integer.parseInt(thisGame.getRepetition())) {
                        		System.out.println(Integer.parseInt(thisGame.getRepetition()));
                        		score.setText(String.valueOf(Integer.parseInt(score.getText())+Integer.parseInt(thisGame.getDifficulty())));
								doNext();
                        	 }
                        	 else {
                        		AppRunningManager.processQuery("Add Record", new String[] {Generator.generateId("Record"), AppRunningManager.getCurrentPatient().getId(),
                     					thisGame.getType(), thisGame.getDifficulty(), thisGame.getRepetition(), score.getText(), Generator.generateDate()});
                        		if(nextButton.isVisible()) {
                        			try {
                        				AppRunningManager.setCurrentGameIndex(AppRunningManager.getCurrentGameIndex()+1);
                        				AppRunningManager.jumpToGame();
                        			}catch(Exception error) {}
                        		}
                        		else {
                        			try {
                        				doQuit(new ActionEvent());
                        			}catch(Exception error) {}
                        		}  		
                        	 }
                        }
                }
            };
            
         soccer.setOnMousePressed(mousePressedHandler);
         soccer.setOnMouseDragged(mouseDraggedHandler);
         soccer.setOnMouseReleased(mouseReleasedHandler);
       
             
    }

}