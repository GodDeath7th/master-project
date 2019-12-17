package model;


public class PatientInfo {
	private String id;
	private String tid;
	private String name;
	private String gender;
	private String age;
	
	
	public PatientInfo() {}
	public PatientInfo(String id, String tid, String name, String gender, String age) {
		this.id = id;
		this.tid = tid;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public void setId(String id) {this.id = id;}
	public String getId() {return id;}
		
	public void setTid(String tid) {this.tid = tid;}
	public String getTid() {return tid;}
		
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	
	public void setGender(String gender) {this.gender = gender;}
	public String getGender() {return gender;}
	
	public void setAge(String age) {this.age = age;}
	public String getAge() {return age;}
	
}