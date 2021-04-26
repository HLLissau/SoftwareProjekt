package timeManagement.Acceptance.Test;

import timeManagement.Project;
import timeManagement.TimeManagement;

public class ProjectHelper {
	private TimeManagement timeManagement;
	private Project project;
	
	public ProjectHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Project registerExampleProject() throws Exception{
		Project p = getProject();
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.timeManagement.createProject(p);
		this.timeManagement.adminlogout();
		
		return p;
		
	}
	public Project getProject() {
		if (project == null) {
			project = exampleProject();
		}
		return project;
	}
	private Project exampleProject() {
		Project p = new Project("some project");
		return p;
	}
}
