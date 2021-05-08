package timeManagement;

import java.util.ArrayList;
import java.util.List;

public class Project extends ActivityAndProjectParent {
	
	
	private Employee projectManager;
	private ArrayList<Activity> activityList = new ArrayList<Activity>();
	
	public Project(String name) {
		super(name);
		
	}


	
	
		
	/*
	 * Set a new project manager
	 */
	public void setProjectManager(Employee employee , Employee manager) throws OperationNotAllowedException {
		Employee e = getEmployee(employee);
		if (this.projectManager==null){
			this.projectManager=employee;
			addEmployee(employee,employee);
		} else {
			isProjectManager(manager);
			this.projectManager=e;
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

	public void addActivity(Activity a, Employee e) throws Exception {
		isProjectManager(e);
		Activity activityexists = getActivity(a.getID());
		if (activityexists ==null) {
			activityList.add(a);
			e.setActivity(activityexists);
		}else {
			throw new OperationNotAllowedException("Activity already added to project");
		}
	}



	public Activity getActivity(int activity) {
		return activityList.stream().filter(a -> a.getID()==activity).findAny().orElse(null);
	}



	public void setActivityTime(Employee employee, int activityID, int time) throws OperationNotAllowedException {
		isProjectManager(employee);
		getActivity(activityID).setBudgettedTime(time);
	}



	public void addEmployee(Employee employee, Employee manager) throws OperationNotAllowedException {
		if (employee.getID() == null) {
			throw new OperationNotAllowedException("Employees without an ID cannot be added");
		}
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



	public void removeActivity(int activityID, Employee employee) throws OperationNotAllowedException {
		isProjectManager(employee);
		Activity activityToRemove =getActivity(activityID);
		
		if (activityToRemove ==null) {
			throw new OperationNotAllowedException("Activity is not in project");
		}
		activityList.remove(activityToRemove);
	}



	public void removeEmployee(Employee employee, Employee projectManager) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		if ((getEmployee(employee)!=null)) {
			canRemoveEmployee(employee);
			employeeList.remove(employee);
		} else {
			throw new OperationNotAllowedException("Employee not found in project");
		}
	}
	private void canRemoveEmployee(Employee employee) throws OperationNotAllowedException {
		for (Activity activity: activityList) {
			
			if (activity.employeeList.contains(employee)) {
				
				throw new OperationNotAllowedException("Employee is working on project");
			}
		}
		
	}


	public void setDescription(Employee projectManager, String description) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		this.description=description;
		
	}

	public void setTime(Employee projectManager, int time) throws OperationNotAllowedException {
		isProjectManager(projectManager);
		setExpectedTime(time);
	}
	
	

	public void removeProjectManager(Employee projectManager) throws OperationNotAllowedException  {
		isProjectManager(projectManager);
		this.projectManager=null;
	}


	public void addEmployeeToActivity(Employee employee, int activityID, Employee manager) throws Exception {
		
		isProjectManager(manager);
		Activity a = getActivity(activityID);
		if(a!=null) {
			a.addEmployee(employee);
			employee.setActivity(a);
		}else {
			throw new Exception("Activity not found in project");
		}
		
	}
	public ArrayList<Activity> getActivityList() {
		return this.activityList;
	}
	
}
