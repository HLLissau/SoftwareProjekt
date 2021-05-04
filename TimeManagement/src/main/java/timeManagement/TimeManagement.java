package timeManagement;

import java.util.ArrayList;
import java.util.Collection;

public class TimeManagement {
	
	
	private static final Employee Null = null;
	private	String adminPassword="adminadmin";
	private boolean adminLoggedIn=false;
	private ArrayList<Employee> employeeList=new ArrayList<>();
	private ArrayList<Project> projectList=new ArrayList<>();
	private RegisterTime registerTime;
	private Collection<Activity> activityList=new ArrayList<>();
	
	public TimeManagement() {
			this.registerTime = new RegisterTime();
		}
	public void adminLogin(String password) {
		if (password.equals(this.adminPassword)) {
			adminLoggedIn=true;
		}
	}
	public void adminlogout() {
		this.adminLoggedIn=false;
	}

	public boolean adminLoggedIn() {
		return this.adminLoggedIn;
	}

	public void createEmployee(Employee e) throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		Employee employee = getEmployee(e.getID());
		if (employee!=null) {
			throw new OperationNotAllowedException("Employee is already registered");
		}
		String id=createID(e.getFirstName(), e.getLastName());
		e.setID(id);
		employeeList.add(e);
	}
	public void createActivity(Activity a) throws OperationNotAllowedException {
		
		Activity activity = getActivity(a.getID());
		
		if (activity!=null) {
			throw new OperationNotAllowedException("Activity is already registered");
		}
		int id =createActivityID();
		a.setID(id);
		activityList.add(a);
	}
	
	public Activity getActivity(int activityID) {
		return activityList.stream().filter(a -> a.getID()==activityID).findAny().orElse(null);
	}
	public Project getProject(int projectID) {
		return projectList.stream().filter(p -> p.getID()==projectID).findAny().orElse(null);
	}
	private void checkIfAdminIsLoggedIn() throws OperationNotAllowedException {
		if (!this.adminLoggedIn) {
			throw new OperationNotAllowedException("Administrator login required");
		}
		
	}
	private String createID(String firstName, String lastName) {
		String initials = firstName.substring(0,1) + lastName.substring(0,1);
		if (!isUniqueEmployeeID(initials)) {
			for (char c0 = 'a'; c0 <= 'z'; c0++) {
				for (char c1 = 'a'; c1 <= 'z'; c1++) {
					for (char c2 = 'a'; c2 <= 'z'; c2++) {
						for (char c3 = 'a'; c3 <= 'z'; c3++) {
							initials = "" + c0 + c1 + c2 + c3;
						}
					}
				}
			}
		}
		return initials;
	}

	public Employee getEmployee(String employeeID) {
		return employeeList.stream().filter(e -> e.getID()==employeeID).findAny().orElse(null);
	}
	public boolean isUniqueEmployeeID(String id) {
		return (!employeeList.stream().filter(e-> id==e.getID()).findAny().isPresent());
	}
	public boolean isUniqueProjectID(int id) {
		return (!projectList.stream().filter(p-> id==p.getID()).findAny().isPresent());
	}
	private int createProjectID(int date) {
		int id= (date*10000)+(int) (Math.random()*1000);
		while(!isUniqueProjectID(id)) {
			id= (date*10000) + (int) (Math.random()*10000);
		}
		return id;
	}
	private int createActivityID() {
		int id= (10000)+(int) (Math.random()*1000);
		while(!isUniqueActivityID(id)) {
			id= (10000) + (int) (Math.random()*10000);
		}
		return id;
	}
	private boolean isUniqueActivityID(int id) {
		return (!activityList.stream().filter(a-> id==a.getID()).findAny().isPresent());
	}
	public void createProject(Project p) throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		Project project = getProject(p.getID());
		if(project!=null) {
			throw new OperationNotAllowedException("Project already exists");
		}
		int id =createProjectID(this.registerTime.getYear());
		p.setID(id);
		projectList.add(p);
	}
	
	public void addActivityToProject(Activity a, int pID, String managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(pID).addActivity(a, manager);
	}
	public void addEmployeeToProject(String employeeID, int projectID, String managerID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		Employee manager = getEmployee(managerID);
		Project p = getProject(projectID);
		p.addEmployee(employee, manager);
	}
	public Employee getEmployeeFromProject(String employeeID, int projectID) {
		Employee employee = getEmployee(employeeID);
		
			return getProject(projectID).getEmployee(employee);
		
	}
	public void setProjectManager(int projectID, String employeeID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		
		getProject(projectID).setProjectManager(employee,employee);
		
	}
	public void removeActivity(int activityID, int projectID, String employeeID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		Activity activity = getActivity(activityID);
		getProject(projectID).removeActivity(activity.getID(),employee);
	}
	public void removeEmployeeFromProject(String employeeID, int projectID, String managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		Employee employee = getEmployee(employeeID);
		getProject(projectID).removeEmployee(employee, manager);
		
	}
	public void removeEmployeeFromTimeManagement(String eID) throws Exception {
		checkIfAdminIsLoggedIn();
		Employee employeeToRemove = getEmployee(eID);
		
		if (employeeToRemove!=null) { // check if Employee is found in project
			if (!employeeNotWorkingOnActivities(employeeToRemove)) { // check the Employee is not working on activities
				throw new Exception("Employee is working on activity");
			}
		} else {
			throw new Exception("Employee not found in TimeManagement");
		}
		employeeList.remove(employeeToRemove);
	}
	private boolean employeeNotWorkingOnActivities(Employee employeeToRemove) {
		return employeeToRemove.getActivityList().isEmpty();
	}
	public void setProjectDescription(String description, int projectID, String managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(projectID).setDescription(manager, description);
	}
	public String getProjectDescription(int projectID)  {
		return getProject(projectID).getDescription();
	}
	public void setTimeOfProject(int time, int projectID, String managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(projectID).setTime(manager, time);
		
		
	}
	public int getProjectTime(int projectID) {
		Project p= getProject(projectID);
		return p.getTime();
		
			
	}
	public void removeProjectManagerFromProject(int projectID, String managerID) throws Exception {
		Project p = getProject(projectID);
		Employee manager = getEmployee(managerID);
		p.removeProjectManager(manager);
		
	}
	public  ArrayList<Employee> listAvailableEmployees(int number) {
		ArrayList<Employee> returnlist = new ArrayList<>();
		for (Employee e: employeeList) {
			if (e.canBeAssigned() < number) {
				returnlist.add(e);
			}
		}
		return returnlist;
		
	}
	public ArrayList<Employee> listEmployeesOnProject(int projectID) {
		Project p = getProject(projectID);
		 return p.listEmployees();
	}
	public void addEmployeeToActivity(String employeeID, int projectID, int activityID, String managerID) throws Exception {
		Project p = getProject(projectID);
		Employee manager = getEmployee(managerID);
		Employee employee = getEmployee(employeeID);
		p.addEmployeeToActivity(employee,activityID,manager);
		
	}
}
