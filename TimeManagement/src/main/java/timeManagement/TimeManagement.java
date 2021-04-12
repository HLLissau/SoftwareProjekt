package timeManagement;

import java.util.ArrayList;

public class TimeManagement {
	
	
	private	String adminPassword="adminadmin";
	private boolean adminLoggedIn=false;
	private ArrayList<Employee> employeeList=new ArrayList<>();;
	
	public TimeManagement() {
			
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
		while(!isUniqueID(id)) {
			id= (int) (Math.random()*10000);
		}
		return id;
	}
	public Employee getEmployee(Employee employee) {
		return employeeList.stream().filter(e -> e.getID()==employee.getID()).findAny().orElse(null);
	}
	public boolean isUniqueID(int id) {
		return (!employeeList.stream().filter(e-> id==e.getID()).findAny().isPresent());
	}
}
