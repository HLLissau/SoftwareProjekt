package timeManagement.Acceptance.Test;

/*
 * Made by : Harald
 */
import timeManagement.Project;
import timeManagement.TimeManagement;

public class ProjectHelper {
	private TimeManagement timeManagement;
	private Project project;
	private Project secondProject;
	
	public ProjectHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Project registerExampleProject() throws Exception{
		Project p = getProject();
		this.timeManagement.adminLogin("adminadmin");
		
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
	
	public Project getSecondProject() {
		if (secondProject == null) {
			secondProject = anotherExampleProject();
		}
		return secondProject;
	}
	
	private Project exampleProject() {
		Project p = new Project("some project");
		return p;
	}
	
	private Project anotherExampleProject() {
		Project p = new Project("some other project");
		return p;
	}
}
