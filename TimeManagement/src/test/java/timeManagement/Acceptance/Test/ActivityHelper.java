package timeManagement.Acceptance.Test;

import timeManagement.Activity;
import timeManagement.Project;
import timeManagement.TimeManagement;

public class ActivityHelper {
	private TimeManagement timeManagement;
	private Project project;
	private Object activity;
	public ActivityHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Activity registerExampleActivity() throws Exception{
		Activity a = getActivity();
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.timeManagement.createProject(p);
		this.timeManagement.adminlogout();
		
		return a;
	}
	private Activity getActivity() {
		if (activity == null) {
			activity = exampleActivity();
		}
		return activity;
	}
	private Activity exampleProject() {
		Activity a = new Activity("example");
		return a;
	}
}
