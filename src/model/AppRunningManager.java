package model;

import java.util.ArrayList;

import animation.CommonAnimation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppRunningManager {
	public static int soccer_guide_step;
	public static double fullscreen_weight = 1280;
	public static double fullscreen_height = 720;
	
	public static Stage rootStage;
	public static Stage popUpStage;
	
	public static Scene rootScene;
	public static Scene popUpScene;
	public static AnchorPane rootPane;
	
	private static String id;
	// user owned data
	private static TherapistInfo currentTherapist;
	
	// data used for transferring value among different interfaces
	private static String rootPage = "";
	
	private static PatientInfo currentPatient;
	private static ArrayList<PatientInfo> currentPatientList;
	private static Plan currentPlan;
	private static ArrayList<Plan> currentPlanList;
	
	private static Game currentGame;
	
	private static ArrayList<Game> currentGameList;
	private static int currentGameIndex;
	private static String highestScore;
	
	private static ArrayList<Record> currentRecordList;
	public static boolean isPlan;
	
	private static ArrayList<Game> recordedGameList=new ArrayList<Game>();
	 
	private static DataAccessor accessor = new DataAccessor("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/ProjectOfStrokeRobot","root","12345678");
	
	
	// this is function used for start whole application
	// it will load login scene and initialize root stage and scene for future scene switch
	public static void start(Stage stage) throws Exception{
		rootStage = stage;
		popUpStage = new Stage();
		rootStage.initStyle(StageStyle.UNDECORATED);
		rootStage.initStyle(StageStyle.TRANSPARENT);
		popUpStage.initStyle(StageStyle.UNDECORATED);
		popUpStage.initStyle(StageStyle.TRANSPARENT);
		popUpStage.initModality(Modality.APPLICATION_MODAL);
		
		loadScene("/view/Login", fullscreen_weight, fullscreen_height);
	}
	
	// this function is used for load different scene according to user's requirement
	public static void loadScene(String sceneName, double weight, double height) throws Exception{
		rootScene = new SceneBuilder().build(sceneName, weight, height);
		rootStage.setScene(rootScene);
		rootStage.show();
	}
	
	// this function is used for create some pop window appearing on base window
	public static void loadPopUpScene(String sceneName, double height, double weight, AnchorPane pane) throws Exception{
		rootPane = pane;
		CommonAnimation.setMask(true, 0 ,0 ,rootPane);
		
		popUpScene = new SceneBuilder().build(sceneName, height, weight);
		popUpStage.setScene(popUpScene);
		popUpStage.showAndWait();
	}
	
	public static void closePopUpScene() {
		CommonAnimation.setMask(false, 0, 0, rootPane);
		popUpScene = null;
		popUpStage.close();
		rootPane = null;
	}
		
	public static void jumpToGame() throws Exception{
		AppRunningManager.loadScene("/view/Calibration",fullscreen_weight, fullscreen_height);
	}
	
	// this function is used for process query from user and if necessary, transport query to database and build feedback which based on database response back to user
	public static boolean processQuery(String request, String[] parameters) {		
		boolean operateFlag = false;
		
		// database access and operation conduct
		Response response = accessor.processQuery(request, parameters);
		if(response.getStatus().equals("success")) {
			operateFlag = true;
			switch(request) {
				case "Login":
					setCurrentTherapist(response.getTherapistInfo());
					break;
				case "Register1":
					setId(parameters[0]);
					break;
				case "Register2":
					setCurrentTherapist(response.getTherapistInfo());
					break;
				case "Get Patient List":
					setCurrentPatientList(response.getPatientList());
					break;
				case "Get Plan List":
					setCurrentPlanList(response.getPlanList());
					break;
				case "Add Patient":
					addPatient(response.getPatient());
					break;
				case "Add Plan":
					addPlan(response.getPlan());
					break;
				case "Remove Plan":
					for(Plan eachPlan: currentPlanList) {
						if(eachPlan.getId().equals(response.getPlan().getId())) {
							currentPlanList.remove(eachPlan);
							break;
						}
					}
					break;
				case "Add Record":
					addRecord(response.getRecord());
					break;	
				case "Get Record List":
					setCurrentRecordList(response.getRecordList());
					break;	
				case "Edit Patient Profile":
					setCurrentPatient(response.getPatient());
					for(int i=0; i< currentPatientList.size(); i++) {
						if(currentPatientList.get(i).getId().equals(response.getPatient().getId())) {
							currentPatientList.set(i, response.getPatient());
							break;
						}
					}
				case "Get Recent Game List":
					setRecordedGameList(response.getRecentGameList());
					break;
				default:
					break;
			}
		}
		return operateFlag;
	}	
	
	
	// use to search user locally
	public static ArrayList<PatientInfo> doLocalSearch(String keyword) {
		ArrayList<PatientInfo> foundPatient = new ArrayList<>();		
		for(PatientInfo eachPatient: currentPatientList) {
			if(eachPatient.getName().toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
				foundPatient.add(eachPatient);
			}
		}		
		return foundPatient;
	}
	public static void setId(String i) {id = i;}
	public static String getId() {return id;}
	
	public static void setCurrentTherapist(TherapistInfo therapist) {currentTherapist = therapist;}
	public static TherapistInfo getCurrentTherapist() {return currentTherapist;}
	
	public static void setCurrentPatientList(ArrayList<PatientInfo> patientList) {currentPatientList = patientList;}
	public static ArrayList<PatientInfo> getCurrentPatientList(){return currentPatientList;}
	
	public static void addPatient(PatientInfo patient) {currentPatientList.add(patient);}
	
	public static void setCurrentPatient(PatientInfo patient) {currentPatient = patient;}
	public static PatientInfo getCurrentPatient() {return currentPatient;}
	
	public static void setCurrentPlan(Plan plan) {currentPlan = plan;}
	public static Plan getCurrentPlan() {return currentPlan;}
	
	public static void setCurrentPlanList(ArrayList<Plan> planList) {currentPlanList = planList;}
	public static ArrayList<Plan> getCurrentPlanList(){return currentPlanList;}
	
	public static void addPlan(Plan plan) {currentPlanList.add(plan);}
	public static void removePlan(Plan plan) {currentPlanList.remove(plan);}
	
	
	
	
		
	public static Game getCurrentGame() {return currentGame;}
	public static void setCurrentGame(Game currentGame) {AppRunningManager.currentGame = currentGame;}
	
	public static int getCurrentGameIndex() {return currentGameIndex;}
	public static void setCurrentGameIndex(int currentGameIndex) {AppRunningManager.currentGameIndex = currentGameIndex;}
	
	public static String getHighestScore() {return highestScore;}
	public static void setHighestScore(String highestScore) {AppRunningManager.highestScore = highestScore;}
	
	public static ArrayList<Game> getCurrentGameList() {return currentGameList;}
	public static void setCurrentGameList(ArrayList<Game> currentGameList) {AppRunningManager.currentGameList = currentGameList;}
	
	public static void setRootPage(String page) {rootPage = page;}
	public static String getRootPage() {return rootPage;}
	
	public static void setCurrentRecordList(ArrayList<Record> recordList) {currentRecordList = recordList;}
	public static ArrayList<Record> getCurrentRecordList() {return currentRecordList;}
	public static void addRecord(Record record) {currentRecordList.add(record);}

	public static ArrayList<Game> getRecordedGameList() {
		return recordedGameList;
	}

	public static void setRecordedGameList(ArrayList<Game> recordedGameList) {
		AppRunningManager.recordedGameList = recordedGameList;
	}
	
}

