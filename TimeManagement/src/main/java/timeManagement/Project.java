package timeManagement;

public class Project extends ActivityAndProjectParent {

		
	private Employee projectManager;

	public Project(String name) {
		super(name);
	}
	
	
	
	public void setProjectManager(Employee manager) {
		this.projectManager=manager;
	}
}
