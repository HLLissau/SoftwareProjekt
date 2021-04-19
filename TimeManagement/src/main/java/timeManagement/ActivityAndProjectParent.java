package timeManagement;

public class ActivityAndProjectParent {
	private String name;
	private String description;
	private int id;
	public ActivityAndProjectParent(String name){
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id=id;
		
	}
	
	public String getName() {
		return this.name;
	}
	
}
