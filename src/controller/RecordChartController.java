package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.AppRunningManager;
import model.Record;

public class RecordChartController {
	ArrayList<Record> thisRecordList = new ArrayList<>();
	Series<String, Number> series = new Series<>();
	
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button basketButton;

    @FXML
    private Button footButton;

    @FXML
    private LineChart<String, Number> progressChart;

    @FXML
    void goBasket(ActionEvent event) {
    	series.getData().clear();
    	footButton.setStyle("-fx-background-color: white;"+
                "-fx-background-radius: 10 0 0 10;"+
                "-fx-border-radius: 10 0 0 10;"+
                "-fx-border-color: #51a8f5");
    	basketButton.setStyle("-fx-background-color: #51a8f5;"+
             "-fx-background-radius: 0 10 10 0;"+
             "-fx-border-radius: 0 10 10 0;"+
             "-fx-border-color: #51a8f5");
    	footButton.setTextFill(Paint.valueOf("#51a8f5"));
    	basketButton.setTextFill(Color.WHITE);
    	progressChart.setTitle("BASKETBALL PROGRESS REPORT");
    	if(thisRecordList.size() > 0) {
    		for(Record eachRecord: thisRecordList) {
    			if(eachRecord.getType().equals("basketball")) {
    				series.getData().add(new XYChart.Data<>(eachRecord.getDate(), Integer.parseInt(eachRecord.getScore())));
    			}
    		}
    	}    
    }

    @FXML
    void goFoot(ActionEvent event) {
    	series.getData().clear();
    	footButton.setStyle("-fx-background-color: #51a8f5;"+
                "-fx-background-radius: 10 0 0 10;"+
	               "-fx-border-radius: 10 0 0 10;"+
                "-fx-border-color: #51a8f5");
    	basketButton.setStyle("-fx-background-color: white;"+
             "-fx-background-radius: 0 10 10 0;"+
             "-fx-border-radius: 0 10 10 0;"+
             "-fx-border-color: #51a8f5");
		footButton.setTextFill(Color.WHITE);
		basketButton.setTextFill(Paint.valueOf("#51a8f5"));
    	progressChart.setTitle("FOOTBALL PROGRESS REPORT");
    	if(thisRecordList.size() > 0) {
    		for(Record eachRecord: thisRecordList) {
    			if(eachRecord.getType().equals("soccer")) {
    				series.getData().add(new XYChart.Data<>(eachRecord.getDate(), Integer.parseInt(eachRecord.getScore())));
    			}
    		}
    	}    
    }

    public void initData(AnchorPane rootPane) {
    	if(AppRunningManager.getCurrentRecordList() != null) {
    		thisRecordList = AppRunningManager.getCurrentRecordList();
    	}
    	
    	progressChart.getXAxis().setLabel("DATE");
    	progressChart.getYAxis().setLabel("SCORE");   	
    	series.setName("SCORE TREND");
    	
    	goFoot(new ActionEvent());
    	
    	progressChart.getData().add(series);
    }
}