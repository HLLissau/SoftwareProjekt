package timeManagement;

import java.util.ArrayList;

public class Employee {

	private String iD;
	private String firstName;
	private String lastName;
	private String email;
	private ArrayList<Activity> presentActivityList;
	private ArrayList<Project> presentprojectList;

	public Employee(String firstName, String lastName, String email) {
		// firstname, lastname must not be less than 2 chars long
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
		this.presentActivityList = new ArrayList<Activity>();
		this.presentprojectList= new ArrayList<Project>();
	}
	public void setID(String iD) {
		this.iD=iD;
	}
	
	public String getID() {
		return this.iD;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public int canBeAssigned() {
		return presentActivityList.size();
	}
	public void setActivity(Activity activity) throws Exception {
//		if (presentActivityList.contains(activity)) {
//			throw new Exception("Employee already added to the activity");
//		}
		this.presentActivityList.add(activity);
	}
	public void setProject(Project project) {
		this.presentprojectList.add(project);
	}
	
	public ArrayList<Project> getProjects() {
		return presentprojectList;
	}
	public boolean canBeRemoved() {
		if (this.presentActivityList.size()>0) {
			return false;
		}
		
		return true;
	}
	public Activity getActivity(int activityID) {
		return presentActivityList.stream().filter(a -> a.getID()==activityID).findAny().orElse(null);
	}
	public void removeActivity(Activity activity) throws Exception {
		this.presentActivityList.remove(activity);
	}
	public ArrayList<Activity> getActivityList() {
		
		return this.presentActivityList;
	}
	public ArrayList<Project> getProjectList() {
		
		return this.presentprojectList;
	}
}
