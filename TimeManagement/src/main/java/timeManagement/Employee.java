package timeManagement;

import java.util.ArrayList;

public class Employee {

	private String iD;
	private String firstName;
	private String lastName;
	private String email;
	private ArrayList<Activity> presentActivityList;
	private ArrayList<Project> presentProjectList;

	public Employee(String firstName, String lastName, String email) {
		// firstname, lastname must not be less than 2 chars long
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
		this.presentActivityList = new ArrayList<Activity>();
		this.presentProjectList= new ArrayList<Project>();
	}
	public void setID(String iD) {
		this.iD=iD;
	}
	
	public String getID() {
		return this.iD;
	}
	
	protected String getFirstName() {
		return firstName;
	}
	
	protected String getLastName() {
		return lastName;
	}

	public int canBeAssigned() {
		return presentActivityList.size();
	}
	public void setActivity(Activity activity) {
		this.presentActivityList.add(activity);
	}
	public ArrayList<Activity> getActivityList() {
		return this.presentActivityList;
	}
	public void setProject(Project project) {
		this.presentProjectList.add(project);
	}
	public boolean canBeRemoved() {
		if (this.presentActivityList.size()==0) {
			return true;
		} else {
			return false;
		}
	}
	public ArrayList<Project> getProjectList() {
		
		return this.presentProjectList;
	}
}
