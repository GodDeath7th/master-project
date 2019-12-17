package model;

public class Record {
	private String id;
	private String pid;
	private String type;
	private String difficulty;
	private String repetition;
	private String score;
	private String date;
	
	public Record() {}
	public Record(String id, String pid, String type, String difficulty, String repetition, String score, String date) {
		this.id = id;
		this.pid = pid;
		this.type = type;
		this.difficulty = difficulty;
		this.repetition = repetition;
		this.score = score;
		this.date = date;
	}
	
	public void setId(String id) {this.id = id;}
	public String getId() {return id;}
	
	public void setPid(String pid) {this.pid = pid;}
	public String getPid() {return pid;}
	
	public void setType(String type) {this.type = type;}
	public String getType() {return type;}
	
	public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
	public String getDifficulty() {return difficulty;}
	
	public void setRepetition(String repetition) {this.repetition = repetition;}
	public String getRepetition() {return repetition;}
	
	public void setScore(String score) {this.score = score;}
	public String getScore() {return score;}
	
	public void setDate(String date) {this.date = date;}
	public String getDate() {return date;}
}
