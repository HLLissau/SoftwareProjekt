package timeManagement.Acceptance.Test;
/*
 * Made by : Erik
 * 
 */
import java.util.List;

import timeManagement.Employee;
import timeManagement.TimeManagement;

public class EmployeeHelper {
	private TimeManagement timeManagement;
	private Employee employee;
	private Employee secondEmployee;

	public EmployeeHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Employee registerExampleEmployee() throws Exception {
		Employee e = getEmployee();
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
			
		}
		this.timeManagement.createEmployee(e);
		this.timeManagement.adminlogout();
		
		return e;
	}
	//overloading
	public Employee registerExampleEmployee(Employee employee) throws Exception {
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
			
		}
		this.timeManagement.createEmployee(employee);
		this.timeManagement.adminlogout();
		
		return employee;
	}
	
	public Employee registerNewExampleEmployee() throws Exception {
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.employee = exampleEmployee();
		this.timeManagement.createEmployee(this.employee);
		this.timeManagement.adminlogout();
		
		return this.employee;
	}
	public Employee registerSecondExampleEmployee() throws Exception {
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.secondEmployee = getSecondEmployee();
		this.timeManagement.createEmployee(this.secondEmployee);
		this.timeManagement.adminlogout();
		
		return this.secondEmployee;
	}
	public Employee registerNewSecondExampleEmployee() throws Exception {
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.secondEmployee = exampleEmployee();
		this.timeManagement.createEmployee(this.secondEmployee);
		this.timeManagement.adminlogout();
		return this.secondEmployee;
	}
	
	public Employee getEmployee() {
		if (employee == null) {
			employee = exampleEmployee();
		}
		return employee;
	}
	public Employee getSecondEmployee() {
		if (secondEmployee == null) {
			secondEmployee = exampleEmployee();
		}
		return secondEmployee;
	}
	
	private Employee exampleEmployee() {
		Employee e = new Employee("Jens", "Hansen", "JHansen@awesomefirm.dk");
		return e;
	}
	
}
