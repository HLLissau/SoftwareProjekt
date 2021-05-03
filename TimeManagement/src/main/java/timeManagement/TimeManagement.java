package timeManagement;

import java.util.ArrayList;

public class TimeManagement {
	
	
	private static final Employee Null = null;
	private	String adminPassword="adminadmin";
	private boolean adminLoggedIn=false;
	private ArrayList<Employee> employeeList=new ArrayList<>();
	private ArrayList<Project> projectList=new ArrayList<>();
	private RegisterTime registerTime;
	private Activity activity;
	
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

	public Employee getEmployee(int employeeID) {
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
	public Project getProject(int projectID) {
		return projectList.stream().filter(p -> p.getID()==projectID).findAny().orElse(null);
	}
	public void addActivityToProject(Activity a, int pID, int managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(pID).addActivity(a, manager);
		
	}
	public void addEmployeeToProject(int employeeID, int projectID, int managerID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		Employee manager = getEmployee(managerID);
		Project p = getProject(projectID);
		p.addEmployee(employee, manager);
	}
	public Employee getEmployeeFromProject(int employeeID, int projectID) {
		Employee employee = getEmployee(employeeID);
		
			return getProject(projectID).getEmployee(employee);
		
	}
	public void setProjectManager(int projectID, int employeeID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		
		getProject(projectID).setProjectManager(employee,employee);
		
	}
	public void removeActivity(Activity activity, int projectID, int employeeID) throws OperationNotAllowedException {
		Employee employee = getEmployee(employeeID);
		getProject(projectID).removeActivity(activity,employee);
	}
	public void removeEmployeeFromProject(int employeeID, int projectID, int managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		Employee employee = getEmployee(employeeID);
		getProject(projectID).removeEmployee(employee, manager);
		
	}
	public void removeEmployeeFromTimeManagement(int eID) throws Exception {
		checkIfAdminIsLoggedIn();
		Employee employeeToRemove = getEmployee(eID);
		
		if (employeeToRemove!=null) { // check if Employee is found in project
			if (employeeNotWorkingOnActivities(employeeToRemove)) { // check the Employee is not working on activities
				employeeList.remove(employeeToRemove);
			} else {
				throw new Exception("Employee is working on activity");
			}
		} else {
			throw new Exception("Employee not found in project");
		}
		
	}
	private boolean employeeNotWorkingOnActivities(Employee employeeToRemove) {
		for(Project project: projectList) {
			project.getActivity(activity);
		}
		return true;
	}
	public void setProjectDescription(String description, int projectID, int managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(projectID).setDescription(manager, description);
	}
	public String getProjectDescription(int projectID) throws OperationNotAllowedException {
		return getProject(projectID).getDescription();
	}
	public void setTimeOfProject(int time, int projectID, int managerID) throws OperationNotAllowedException {
		Employee manager = getEmployee(managerID);
		getProject(projectID).setTime(manager, time);
		
		
	}
	public int getProjectTime(int projectID) throws Exception {
		Project p= getProject(projectID);
		if (p!=null) {
			return p.getTime();
		} else {
			throw new Exception("project not found");
		}
			
	}
	public void removeProjectManagerFromProject(int projectID, int managerID) throws Exception {
		Project p = getProject(projectID);
		Employee manager = getEmployee(managerID);
		if(p!=null) {
			p.removeProjectManager(manager);
		}else {
			throw new Exception("project not found");
		}
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
}
