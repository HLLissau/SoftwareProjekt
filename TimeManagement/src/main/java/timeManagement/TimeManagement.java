package timeManagement;

import java.util.ArrayList;

public class TimeManagement {
	
	
	private	String adminPassword="adminadmin";
	private boolean adminLoggedIn=false;
	private Employee employee;
	private ArrayList<Employee> employeeList=new ArrayList<>();;
	
	public TimeManagement() {
			
		}
	public void adminLogin(String password) {
		if (password.equals(this.adminPassword)) {
			adminLoggedIn=true;
		}
	}

	public boolean adminLoggedIn() {
		return this.adminLoggedIn;
	}

	public void createEmployee(Employee employee) {
		if (this.adminLoggedIn) {
			int id=createID();
			employee.setID(id);
			employeeList.add(employee);
			
		}
	}
	private int createID() {
		int id= (int) (Math.random()*10000);
		while(!isUniqueID(id)) {
			id= (int) (Math.random()*10000);
		}
		return id;
	}
	public Employee getEmployee(Employee employee) {
		return employeeList.stream().filter(e -> e.getID()==employee.getID()).findAny().orElse(null);
	}
	private  boolean isUniqueID(int id) {
		return (!employeeList.stream().filter(e-> id==e.getID()).findAny().isPresent());
	}
}
