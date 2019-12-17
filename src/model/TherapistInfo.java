package model;

public class TherapistInfo {
	private String id;
	private String name;
	private String secret;
	
	public TherapistInfo(String id, String name, String secret) {
		this.id = id;
		this.name = name;
		this.secret = secret;
	}
	
	public void setId(String id){this.id = id;}
	public String getId() {return id;}

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	
	public void setSecret(String secret) {this.secret = secret;}
	public String getSecret() {return secret;}
}
