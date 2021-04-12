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
		if(!this.timeManagement.adminLoggedIn()) {
			this.timeManagement.adminlogout();
		}
		return e;
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
