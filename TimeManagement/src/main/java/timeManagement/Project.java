package timeManagement;

import java.util.ArrayList;
import java.util.List;

public class Project extends ActivityAndProjectParent {

		
	private Employee projectManager;
	private List<Activity> activityList = new ArrayList<Activity>();
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	public Project(String name) {
		super(name);
	}
	
		
	/*
	 * Set a new project manager
	 */
	public void setProjectManager(Employee newManager , Employee manager) throws OperationNotAllowedException {
		if (this.projectManager==null){
			this.projectManager=newManager;
		} else {
			isProjectManager(manager);
			this.projectManager=newManager;
		}
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
		Activity activityexists = getActivity(a);
		if (activityexists ==null) {
			activityList.add(a);
			e.setActivity(activityexists);
		}else {
			throw new OperationNotAllowedException("Activity already added to project");
		}
	}



	public Activity getActivity(Activity activity) {
		return activityList.stream().filter(a -> a.getID()==activity.getID()).findAny().orElse(null);
	}



	public void setActivityTime(Employee employee, Activity activity, int time) throws OperationNotAllowedException {
		isProjectManager(employee);
		getActivity(activity).setBudgettedTime(time);
	}



	public void addEmployee(Employee employee, Employee manager) throws OperationNotAllowedException {
		isProjectManager(manager);
			
		if ((getEmployee(employee)==null)) {
			employeeList.add(employee);
			employee.setProject(this);
		} else {
			throw new OperationNotAllowedException("Employee already added to project");
		}
	}



	public Employee getEmployee(Employee employee) {
		return employeeList.stream().filter(e -> e.getID()==employee.getID()).findAny().orElse(null);
	}



	public void removeActivity(Activity activity, Employee employee) throws OperationNotAllowedException {
		isProjectManager(employee);
		Activity activityToRemove =getActivity(activity);
		
		if (activityToRemove !=null) {
			activityList.remove(activityToRemove);
		}else {
			throw new OperationNotAllowedException("Activity is not in project");
		}	
	}



	public void removeEmployee(Employee employee, Employee projectManager) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		if ((getEmployee(employee)!=null)) {
			employeeList.remove(employee);
		} else {
			throw new OperationNotAllowedException("Employee not found in project");
		}
	}
	public void setDescription(Employee projectManager, String description) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		this.description=description;
		
	}

	public void setTime(Employee projectManager, int time) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		assert time>0 : "precondition not met, must be positive time";
		setExpectedTime(time);
		assert this.time==time: "postcondition not met";
	}

	public int getTime() {
		
		return this.time;
	}

	public void removeProjectManager(Employee projectManager) throws OperationNotAllowedException  {
		isProjectManager(projectManager);
		this.projectManager=null;
	}
	
}
