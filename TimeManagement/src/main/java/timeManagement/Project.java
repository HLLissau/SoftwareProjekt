package timeManagement;

import java.util.ArrayList;
import java.util.List;

public class Project extends ActivityAndProjectParent {

		
	private Employee projectManager;
	private List<Activity> activityList = new ArrayList<Activity>();
	
	public Project(String name) {
		super(name);
	}
	
	
	
	public void setProjectManager(Employee manager) {
		this.projectManager=manager;
	}

	public void isProjectManager(Employee e) throws OperationNotAllowedException {
		if (!e.equals(projectManager)) {
			throw new OperationNotAllowedException("Not logged in as project manager");
		}
	}
	
	public Employee getProjectManager() {
		return projectManager;
	}

	public void addActivity(Activity a, Employee e) throws OperationNotAllowedException {
		isProjectManager(e);
		activityList.add(a);
		
	}



	public Activity getActivity(Activity activity) {
		return activityList.stream().filter(a -> a.getID()==activity.getID()).findAny().orElse(null);
	}



	public void setActivityTime(Employee employee, Activity activity, int time) throws OperationNotAllowedException {
		isProjectManager(employee);
		getActivity(activity).setBudgettedTime(time);
	}
}
