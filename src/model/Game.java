package model;

// object class to store game data in memory
public class Game {
	private String time;
	private String type;
	private String difficulty;
	private String repetition;
	public Game() {}
	public Game(String type, String difficulty, String repetition)
	{
		this.type = type;
		this.difficulty = difficulty;
		this.repetition = repetition;
	}
	
	
	public void setType(String type) {this.type = type;}
	public String getType() {return type;}
	
	public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
	public String getDifficulty() {return difficulty;}
	
	public void setRepetition(String repetition) {this.repetition = repetition;}
	public String getRepetition() {return repetition;}
	
	public void setTime(String time) {this.time = time;}
	public String getTime() {return time;}
	
	public void storeGameRecord(int score){System.out.println("storing record: "+score);}
	public void getHighestRecord(){System.out.println("getting highest record");}
	
	
}
