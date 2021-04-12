package timeManagement;

import java.util.ArrayList;

public class TimeManagement {
	
	
	private	String adminPassword="adminadmin";
	private boolean adminLoggedIn=false;
	private ArrayList<Employee> employeeList=new ArrayList<>();
	private ArrayList<Project> projectList=new ArrayList<>();
	private RegisterTime registerTime;;
	
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

	public void addEmployee(Employee e) throws OperationNotAllowedException {
		checkIfAdminIsLoggedIn();
		Employee employee = getEmployee(e);
		if (employee!=null) {
			throw new OperationNotAllowedException("Employee is already registered");
		}
		employeeList.add(e);
		
		
	}
	private void checkIfAdminIsLoggedIn() throws OperationNotAllowedException {
		if (!this.adminLoggedIn) {
			throw new OperationNotAllowedException("Administrator login required");
		}
		
	}
	public int createID() {
		int id= (int) (Math.random()*10000);
		while(!isUniqueEmployeeID(id)) {
			id= (int) (Math.random()*10000);
		}
		return id;
	}
	public Employee getEmployee(Employee employee) {
		return employeeList.stream().filter(e -> e.getID()==employee.getID()).findAny().orElse(null);
	}
	public boolean isUniqueEmployeeID(int id) {
		return (!employeeList.stream().filter(e-> id==e.getID()).findAny().isPresent());
	}
	public boolean isUniqueProjectID(int id) {
		return (!projectList.stream().filter(p-> id==p.getID()).findAny().isPresent());
	}
	public int createProjectID(int date) {
		int id= (date*10000)+(int) (Math.random()*1000);
		while(!isUniqueProjectID(id)) {
			id= (date*10000) + (int) (Math.random()*10000);
		}
		return id;
	}
	public void createProject(Project p) throws OperationNotAllowedException {
		Project project = getProject(p);
		createProjectID(this.registerTime.getYear());
		if(project!=null) {
			throw new OperationNotAllowedException("Project already exists");
		}
		projectList.add(p);
	}
	public Project getProject(Project project) {
		return projectList.stream().filter(p -> p.getID()==project.getID()).findAny().orElse(null);
	}
}
