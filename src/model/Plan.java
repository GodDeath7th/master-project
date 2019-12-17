package model;

public class Plan {
	private String id;
	private String pid;
	private String content;
	
	public Plan() {}
	public Plan(String id, String pid, String content) {
		this.id = id;
		this.pid = pid;
		this.content = content;
		
	}
	
	public void setId(String id) {this.id = id;}
	public String getId() {return id;}
	
	public void setPid(String pid) {this.pid = pid;}
	public String getPid() {return pid;}
	
	public void setContent(String content) {this.content = content;}
	public String getContent(){return content;}
	
}
