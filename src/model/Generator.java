package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Generator {
	// this is used for generate unique id as primary field for each table in database
	public static String generateId(String type) {
		String id = "";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		id = type+dateFormat.format(date);
		return id;
	}
	
	// this is used for create option of 1 to 130 for age
	public static ObservableList<String> generateOption(String type){
		ObservableList<String> option = FXCollections.observableArrayList();
		
		switch(type) {
		case "Age":
			for(int i=1; i<130; i++) {
				option.add(String.valueOf(i));
			}
			break;
		default:break;
		}	
		return option;
	}
	
	// this is used for generate date for each game performance report
	public static String generateDate() {
		Date date = new Date();  
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateStr = format.format(date);
	    
	    return dateStr;
	}
}
