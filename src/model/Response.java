package model;

import java.util.ArrayList;

// this class is used for store response report from database
public class Response {
	private String status;

	private TherapistInfo therapist;
	private ArrayList<PatientInfo> patientList;
	private PatientInfo patient;
	private ArrayList<Record> recordList;
	private ArrayList<Plan> planList;
	private Plan plan;
	private Record record;
	private ArrayList<Game> recentGameList;

	public ArrayList<Game> getRecentGameList() {
		return recentGameList;
	}
	public void setRecentGameList(ArrayList<Game> recentGameList) {
		this.recentGameList = recentGameList;
	}
	public void setTherapistInfo(TherapistInfo therapist) {this.therapist = therapist;}
	public TherapistInfo getTherapistInfo() {return therapist;}
	
	public void setPatient(PatientInfo patient) {this.patient = patient;}
	public PatientInfo getPatient() {return patient;}
	
	public void setPatientList(ArrayList<PatientInfo> patientList) {this.patientList = patientList;}
	public ArrayList<PatientInfo> getPatientList(){ return patientList;}
	
	public void setRecordList(ArrayList<Record> recordList) {this.recordList = recordList;}
	public ArrayList<Record> getRecordList() {return recordList;}
	
	public void setPlanList(ArrayList<Plan> planList) {this.planList = planList;}
	public ArrayList<Plan> getPlanList() {return planList;}
	
	public void setPlan(Plan plan) {this.plan = plan;}
	public Plan getPlan() {return plan;}
	
	public void setStatus(String status) {this.status = status;}
	public String getStatus() {return status;}
	
	public void setRecord(Record record) {this.record = record;}
	public Record getRecord() {return record;}
}
