package timeManagement.Acceptance.Test;

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
	
	public Employee registerNewExampleEmployee() throws Exception {
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
		Employee e = new Employee("Jens Hansen","JHansen@awesomefirm.dk");
		return e;
	}
}
