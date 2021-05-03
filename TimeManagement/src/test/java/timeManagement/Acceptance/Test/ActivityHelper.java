package timeManagement.Acceptance.Test;

import timeManagement.Activity;
import timeManagement.Employee;
import timeManagement.Project;
import timeManagement.TimeManagement;

public class ActivityHelper {
	private TimeManagement timeManagement;
	private Activity activity;
	private String name;
	public ActivityHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Activity registerExampleActivity(String name, int pID, String eID) throws Exception{
		this.name = name;
		Activity a = getActivity();
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		
		this.timeManagement.addActivityToProject(a, pID, eID);
		this.timeManagement.adminlogout();
		
		return a;
	}
	
	
	
	public Activity getActivity() {
		if (activity == null) {
			activity = exampleActivity();
		}
		return activity;
	}
	private Activity exampleActivity() {
		Activity a = new Activity(this.name);
		return a;
	}
}
