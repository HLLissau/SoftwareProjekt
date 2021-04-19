package timeManagement.Acceptance.Test;

import timeManagement.Employee;
import timeManagement.TimeManagement;

public class EmployeeHelper {
	private TimeManagement timeManagement;
	private Employee employee;

	public EmployeeHelper(TimeManagement timeManagement) {
		this.timeManagement= timeManagement;
	}
	public Employee registerExampleEmployee() throws Exception {
		Employee e = getEmployee();
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
			
		}
		this.timeManagement.addEmployee(e);
		this.timeManagement.adminlogout();
		
		return e;
	}
	
	public Employee registerNewExampleEmployee() throws Exception {
		if(!timeManagement.adminLoggedIn()) {
			this.timeManagement.adminLogin("adminadmin");
		}
		this.employee = exampleEmployee();
		this.timeManagement.addEmployee(this.employee);
		this.timeManagement.adminlogout();
		
		return this.employee;
	}
	
	public Employee getEmployee() {
		if (employee == null) {
			employee = exampleEmployee();
		}
		return employee;
	}
	private Employee exampleEmployee() {
		Employee e = new Employee("Jens Hansen","JHansen@awesomefirm.dk");
		e.setID(timeManagement.createID());
		return e;
	}
}
