package timeManagement;

import java.util.ArrayList;

public class Employee {

	private int iD;
	private String name;
	private String email;
	private ArrayList<Activity> presentActivityList;
	private ArrayList<Project> presentprojectList;

	public Employee(String name, String email) {
		this.name=name;
		this.email=email;
		this.presentActivityList = new ArrayList<Activity>();
		this.presentprojectList= new ArrayList<Project>();
		
	}
	public void setID(int iD) {
		this.iD=iD;
	}
	
	public int getID() {
		return this.iD;
	}
	
	
	public int canBeAssigned() {
		return presentActivityList.size();
	}
	public void setActivity(Activity activity) {
		this.presentActivityList.add(activity);
	}
	public void setProject(Project project) {
		this.presentprojectList.add(project);
		
	}

}
