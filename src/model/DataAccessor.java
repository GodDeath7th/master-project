package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// this class defines standard way to access and disconnect with database
// this class also defines add/search/update/delete operation for specific user query
public class DataAccessor {
	private String DRIVER;
	private String DB_URL;
	private String DB_USER;
	private String DB_SECRET;
	
	private Connection connection;
	
	// set password ,username, and database path
	public DataAccessor(String driver, String url, String user, String secret) {
		setDriver(driver);
		setDBLocation(url);
		setUserPair(user, secret);
		
		try {
			Class.forName(DRIVER);
			connection =  DriverManager.getConnection(DB_URL+"?useSSL=false&serverTimezone=UTC", DB_USER, DB_SECRET);
		}catch (Exception error) {
			;
		}
	}
	
	// process query from user and generate process report back to them
	public Response processQuery(String queryType, String[] parameters) {
		try {
			PreparedStatement statement;
			String sql;
			ResultSet result;
			Response response = new Response();
			response.setStatus("fail");
			
			switch(queryType) {
			case "Login":
				sql = "SELECT * FROM therapist WHERE id = ? AND secret = ?";
				statement = connection.prepareStatement(sql);
				for(int i=0; i<2; i++) {
					statement.setString(i+1, parameters[i]);
				}
				try {
					result = statement.executeQuery();	
					while(result.next()) {		
						TherapistInfo thisTherapist = new TherapistInfo(result.getString("id"), result.getString("name"), result.getString("secret"));
						response.setStatus("success");
						response.setTherapistInfo(thisTherapist);
					}
				}catch(Exception error) {}
				break;
			case "Register1":
				sql = "SELECT * FROM worker WHERE id = ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, parameters[0]);
				try {
					result = statement.executeQuery();			
					while(result.next()) {
						if(result.getString("flag").equals("1"))
							break;
						else
							response.setStatus("success");
					}
				}catch(Exception error) {}
				break;
			case "Register2":
				// 0 id, 1 name, 2secret
				sql = "INSERT INTO therapist VALUES (?,?,?)";
				statement = connection.prepareStatement(sql);
				for(int i=0; i<3; i++) {
					statement.setString(i+1, parameters[i]);
				}
				int status = statement.executeUpdate();
				
				sql = "UPDATE worker SET flag = '1' WHERE id = ?";
				statement = connection.prepareStatement(sql);
				for(int i=0; i<1; i++) {
					statement.setString(i+1, parameters[i]);
				}
				
				int status1 = statement.executeUpdate();
				if(status1 > 0 && status > 0) {
					TherapistInfo newTherapist = new TherapistInfo(parameters[0], parameters[1], parameters[2]);
					response.setStatus("success");
					response.setTherapistInfo(newTherapist);
				}				
				break;
			case "Add Patient":
				// 0 id, 1 therapist id, 2 name, 3 gender, 4 age
				sql = "INSERT INTO patient VALUES (?,?,?,?,?)";
				statement = connection.prepareStatement(sql);
				for(int i=0; i<5; i++) {
					statement.setString(i+1, parameters[i]);
				}
				if(statement.executeUpdate() > 0) {
					response.setStatus("success");
					response.setPatient(new PatientInfo(parameters[0], parameters[1],parameters[2],parameters[3],parameters[4]));
				}
				break;
			case "Get Patient List":
				ArrayList<PatientInfo> PatientList = new ArrayList<PatientInfo>();	
				sql = "SELECT * FROM patient WHERE tid = ?";	
				statement = connection.prepareStatement(sql);
				statement.setString(1, parameters[0]);
				try {
					result = statement.executeQuery();
					while(result.next()) {
						PatientInfo patientInfo=new PatientInfo(result.getString("id"), result.getString("tid"), result.getString("name"),
								result.getString("gender"), result.getString("age"));
						PatientList.add(patientInfo);
					}
				}catch(Exception error) {}				
				response.setStatus("success");
				response.setPatientList(PatientList);
				break;				
			case "Store Game Record":
				sql="INSERT INTO record VALUES (?,?,?,?,?,?)";
				statement=connection.prepareStatement(sql);
				for(int i=0;i<6;i++)
				{
					statement.setString(i+1, parameters[i]);
				}
				if(statement.executeUpdate() > 0) {
					response.setStatus("success");
				}
				break;
			case "Get Highest Score":
				sql="SELECT * FROM record WHERE score=(SELECT MAX(score) FROM record) AND pid= ? AND type =?";
				statement=connection.prepareStatement(sql);
				for(int i=0;i<2;i++)
				{
					statement.setString(i+1, parameters[i]);
				}
				boolean isGottenScore=false;
				try {
					result=statement.executeQuery();
					while (result.next()) {
						AppRunningManager.setHighestScore(result.getString("score"));
						isGottenScore=true;
					}
				}catch(Exception error) {}
				if(isGottenScore)
				{
					response.setStatus("success");
				}
				break;
			case "Get Record List":
				ArrayList<Record> recordList = new ArrayList<>();
				sql = "SELECT * FROM record WHERE pid = ?";
				statement=connection.prepareStatement(sql);
	            statement.setString(1, parameters[0]);
	            try {
	            	result=statement.executeQuery();			
	            	while (result.next()) {
	            		recordList.add(new Record(result.getString("id"),result.getString("pid"),result.getString("type"),result.getString("difficulty"),
	            				result.getString("repetition"),result.getString("score"), result.getString("date")));
	            	}
				}catch(Exception error) {}		
				response.setStatus("success");
				response.setRecordList(recordList);
				break;
			case "Get Plan List":
				ArrayList<Plan> planList = new ArrayList<>();
				sql = "SELECT * FROM plan WHERE pid = ?";
				statement=connection.prepareStatement(sql);
				statement.setString(1, parameters[0]);
				try {
					result=statement.executeQuery();
					while(result.next()) {
						planList.add(new Plan(result.getString("id"), result.getString("pid"), result.getString("content")));
					}
				}catch(Exception error) {}
				response.setStatus("success");
				response.setPlanList(planList);
				break;
			case "Add Plan":
				sql = "INSERT INTO plan VALUES (?,?,?)";
				statement=connection.prepareStatement(sql);			
				for(int i=0;i<3;i++)
				{
					statement.setString(i+1, parameters[i]);
				}
				if(statement.executeUpdate() > 0) {
					response.setStatus("success");
					response.setPlan(new Plan(parameters[0], parameters[1], parameters[2]));
				}
				break;
			case "Remove Plan":
				sql = "DELETE FROM plan WHERE id = ?";
				statement=connection.prepareStatement(sql);
				statement.setString(1, parameters[0]);
				if(statement.executeUpdate() > 0) {
					response.setStatus("success");
					response.setPlan(new Plan());
					response.getPlan().setId(parameters[0]);
				}
				break;
			case "Add Record":
				sql = "INSERT INTO record VALUES (?,?,?,?,?,?,?)";
				statement=connection.prepareStatement(sql);			
				for(int i=0;i<7;i++)
				{
					statement.setString(i+1, parameters[i]);
				}
				if(statement.executeUpdate() > 0) {
					response.setStatus("success");
					response.setRecord(new Record(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6]));
				}
				break;
			case "Edit Patient Profile":
				sql = "UPDATE patient SET name = ?, gender = ?, age = ? WHERE id = ?";
				statement=connection.prepareStatement(sql);			
				for(int i=2;i<5;i++)
				{
					statement.setString(i-1, parameters[i]);
				}
				statement.setString(4, parameters[0]);
				if(statement.executeUpdate() > 0) {
					System.out.println("xxxxxxxxxxxxxx");
					response.setStatus("success");
					response.setPatient(new PatientInfo(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]));
				}
				break;	
				
			case "Get Recent Game List":
				ArrayList<Game> gameList=new ArrayList<Game>();
				sql="SELECT * FROM record WHERE pid = ?";
				statement =connection.prepareStatement(sql);
				statement.setString(1, parameters[0]);
				try{
					result=statement.executeQuery();
					while(result.next()) {
						gameList.add(new Game(result.getString("type"), result.getString("difficulty"), result.getString("repetition")));
						gameList.get(gameList.size()-1).setTime(result.getString("date"));
					}
				}catch(Exception error) {}
				response.setStatus("success");
				response.setRecentGameList(gameList);
				break;
			default:
				break;
				
			}			
			return response;
		}catch(Exception error) {			
		}
		return null;		
	}
	
	public void setDriver(String driver) {DRIVER = driver;}
	public void setDBLocation(String url) {DB_URL = url;}
	public void setUserPair(String user, String secret) {
		DB_USER = user; 
		DB_SECRET = secret;
	}
	
	// close connection
	public void closeConnection() throws Exception{
		connection.close();
	}
}
