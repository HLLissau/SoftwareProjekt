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
		Employee employee = getEmployee(e);
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
	
	
	public Employee getEmployee(Employee employee) throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		return employeeList.stream().filter(e -> e.getID()==employee.getID()).findAny().orElse(null);
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
		Project project = getProject(p);
		if(project!=null) {
			throw new OperationNotAllowedException("Project already exists");
		}
		int id =createProjectID(this.registerTime.getYear());
		p.setID(id);
		projectList.add(p);
	}
	public Project getProject(Project project) {
		return projectList.stream().filter(p -> p.getID()==project.getID()).findAny().orElse(null);
	}
	public void addActivityToProject(Activity a, Project p, Employee manager) throws OperationNotAllowedException {
		getProject(p).addActivity(a, manager);
		
	}
	public void addEmployeeToProject(Employee employee, Project project, Employee manager) throws OperationNotAllowedException {
		getProject(project).addEmployee(employee, manager);
		
	}
	public Employee getEmployeeFromProject(Employee employee, Project project) {
		return getProject(project).getEmployee(employee);
	}
	public void setProjectManager(Project project, Employee employee) {
		getProject(project).setProjectManager(employee);
		
	}
	public void removeActivity(Activity activity, Project project, Employee employee) throws OperationNotAllowedException {
		getProject(project).removeActivity(activity,employee);
	}
	public void removeEmployeeFromProject(Employee employee, Project project, Employee projectManager) throws OperationNotAllowedException {
		getProject(project).removeEmployee(employee, projectManager);
		
	}
}
