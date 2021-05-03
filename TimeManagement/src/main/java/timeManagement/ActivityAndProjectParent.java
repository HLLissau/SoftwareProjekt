package timeManagement;

public class ActivityAndProjectParent {
	protected String name;
	protected String description;
	private int id;
	protected int time;
	public ActivityAndProjectParent(String name){
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	protected void setID(int id) {
		this.id=id;
		
	}
	
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	protected void setExpectedTime(int time) {
		this.time=time;
	}
}
