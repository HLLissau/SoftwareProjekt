package timeManagement;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Date;

/*
 * Made by : Erik, Anton and Harald combined
 */

public class TimeManagement {
	private	String adminPassword = "adminadmin"; 
	private boolean adminLoggedIn = false;
	private ArrayList<Employee> employeeList = new ArrayList<>();
	private ArrayList<Project> projectList = new ArrayList<>();
	private RegisterTime registerTime;
	private ArrayList<Activity> activityList = new ArrayList<>();
	private DateServer dateServer = new DateServer();
	
	public TimeManagement() {
		this.registerTime = new RegisterTime();	
	}
	
	public RegisterTime getRegisterTime() throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		return this.registerTime;
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

	public void createEmployee(Employee e) throws Exception {
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
	
	// public for testing
	public String createID(String firstName, String lastName) throws Exception {
		String initials = firstName.substring(0,2).toUpperCase() + lastName.substring(0,2).toUpperCase();
		if (amountOfEmployeesWithID(initials) > 0) { // > 0 => ID is not unique
			initials = getAvailableID(initials);
		}
		return initials;
	}
	
	private String getAvailableID(String initials) throws Exception {
		for (char c0 = 'A'; c0 <= 'Z'; c0++) {
			for (char c1 = 'A'; c1 <= 'Z'; c1++) {
				for (char c2 = 'A'; c2 <= 'Z'; c2++) {
					for (char c3 = 'A'; c3 <= 'Z'; c3++) {
						initials = "" + c0 + c1 + c2 + c3;
						if (amountOfEmployeesWithID(initials) == 0) return initials; // no initials in the list, means that the current initial is unique
					}
				}
			}
		}
		throw new Exception("No available ID is left!");
	}

	public Employee getEmployee(String employeeID) {
		return employeeList.stream().filter(e -> e.getID().equals(employeeID)).findAny().orElse(null);
	}
	
	public long amountOfEmployeesWithID(String id) {
		return employeeList.stream().filter(e-> id.equals(e.getID())).count();
	}
	
	public long amountOfProjectsWithID(int id) {
		return projectList.stream().filter(p-> id==p.getID()).count();
	}	
	
	public int createProjectID() {
		int date =dateServer.getYear();
		int i=1;
		int id= (date*10000)+i;
		while(amountOfProjectsWithID(id) > 0) { // if there is more than 0 projects with the project ID, it is not unique
			i++;
			id= (date*10000) + i;
		}
		return id;
	}
	
	public int createActivityID() {
		int id = 1;
		while(amountOfActivitiesWithID(id) > 0) { // if amount of activities with that ID is NOT 0, then the ID is not unique 
			id++;
		}
		return id;
	}
	
	public long amountOfActivitiesWithID(int id) {
		return activityList.stream().filter(a -> id == a.getID()).count();
	}
	
	public void createProject(Project p) throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		Project project = getProject(p.getID());
		if(project!=null) {
			throw new OperationNotAllowedException("Project already exists");
		}
		int id =createProjectID();
		p.setID(id);
		projectList.add(p);
	}

	public void addActivityToProject(Activity a, int pID, String managerID) throws Exception {
		Employee manager = getEmployee(managerID);
		getProject(pID).addActivity(a, manager);
		this.activityList.add(a);
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
	
	public void setProjectManager(int projectID, String employeeID,String managerID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		Employee manager = getEmployee(managerID); 
		
		getProject(projectID).setProjectManager(employee,manager);
		
	}
	
	public void removeActivity(int activityID, int projectID, String employeeID) throws Exception {
		Employee employee = getEmployee(employeeID);
		Activity activity = getActivity(activityID);
		Project project =getProject(projectID);
		if (project!=null) {
			project.removeActivity(activity.getID(),employee);
		}
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
			if (!employeeToRemove.canBeRemoved()) { // check the Employee is not working on activities
				throw new Exception("Employee is working on activity");
			}
		} else {
			throw new Exception("Employee not found in TimeManagement");
		}
		employeeList.remove(employeeToRemove);
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
	
	public void beginWorkOnActivity(String employeeID, int activityID) throws OperationNotAllowedException {
		Employee e = getEmployee(employeeID);
		Activity a = e.getActivity(activityID);
		
		if (a == (null)) {
			throw new OperationNotAllowedException("Activity not found");
		}
		registerTime.setBeginTime(a, e,dateServer.getTime().getTime());
	}
	
	public Date stopWorkOnActivity(String employeeID, int activityID) throws Exception {
		Employee employee = getEmployee(employeeID);
		Activity activity = getActivity(activityID);
		
		return registerTime.setFinishedTime(activity, employee, dateServer.getTime().getTime());
	}
	
	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public int getTimeSpentOnActivity(int activityID) throws OperationNotAllowedException {
		Activity a = getActivity(activityID);
		if (a ==null) {
			throw new OperationNotAllowedException("Activity not found");
		}
		
		return a.getTimeSpent();
	}

	public void removeEmployeeFromActivity(String employeeID, int activityID) throws Exception {
		Activity a = getActivity(activityID);
		Employee e = getEmployee(employeeID);
		a.removeEmployee(e);
		e.removeActivity(a);
	}
	public ArrayList<Employee> getAllEmployees() {
		//adminLoggedIn();
		return this.employeeList;
	}
	public ArrayList<Project> getAllProjects() {
		return this.projectList;
	}
	public ArrayList<Activity> getEmployeeActivityList(String eID) {
		Employee e = getEmployee(eID);
		return e.getActivityList();
	}
	public ArrayList<Project> getEmployeeProjectList(String eID) {
		Employee e = getEmployee(eID);
		return e.getProjectList();
	}

	public int getTimeSpentOnProject(int id) throws OperationNotAllowedException {
		Project p = getProject(id);
		if (p ==null) {
			throw new OperationNotAllowedException("Project not found");
		}
		
		return p.getTimeSpent();
	}
	
}
