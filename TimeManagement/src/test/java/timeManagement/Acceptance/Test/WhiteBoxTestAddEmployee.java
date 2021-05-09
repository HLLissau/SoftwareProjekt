package timeManagement.Acceptance.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import timeManagement.Employee;
import timeManagement.OperationNotAllowedException;
import timeManagement.Project;
import timeManagement.TimeManagement;

class WhiteBoxTestAddEmployee {
	Project project = new Project("Some project");
	TimeManagement timeManagement = new TimeManagement(); // purely used for ID creation
	Employee isManager = new Employee("Jens", "Hansen", "email");
	Employee inProject = new Employee("some first name", "some second name", "some email");
	
	@Test // A1
	void nullEmployeeButValidProjectManager() throws Exception {
		isManager.setID(timeManagement.createID(isManager.getFirstName(), isManager.getLastName()));
		
		Employee employeeToAdd = null;
		project.setProjectManager(isManager, isManager);
		assertEquals(isManager, project.getProjectManager());
		try {
			project.addEmployee(employeeToAdd, isManager); // will never be green, otherwise the test fails
			fail("No null pointer exception thrown!");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test // A2
	void employeeWithNullIdButValidProjectManager() throws Exception {
		isManager.setID(timeManagement.createID(isManager.getFirstName(), isManager.getLastName()));
		
		Employee employeeToAdd = new Employee("AA", "BB", "CC");
		assertEquals(null, employeeToAdd.getID());
		project.setProjectManager(isManager, isManager);
		assertEquals(isManager, project.getProjectManager());
		try {
			project.addEmployee(employeeToAdd, isManager); // will never be green, otherwise the test fails
			fail("No exception thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
	
	@Test // B1
	void nullManager() throws Exception {
		isManager = null;
		assertEquals(null, isManager);
		Employee employeeToAdd = new Employee("AA", "BB", "CC");
		employeeToAdd.setID(timeManagement.createID(employeeToAdd.getFirstName(), employeeToAdd.getLastName()));
		try {
			project.addEmployee(employeeToAdd, isManager); // will never be green, otherwise the test fails
			fail("No exception thrown!");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test // B2
	void nonProjectManager() throws Exception {
		Employee employeeToAdd = new Employee("AA", "BB", "CC");
		employeeToAdd.setID(timeManagement.createID(employeeToAdd.getFirstName(), employeeToAdd.getLastName()));
		try {
			project.addEmployee(employeeToAdd, isManager); // will never be green, otherwise the test fails
			fail("No exception thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
	
	@Test // C1
	void employeeAddedToProjectWhenNotAlreadyAddedToProject() throws Exception {
		Employee employeeToAdd = new Employee("AA", "BB", "CC");
		employeeToAdd.setID(timeManagement.createID(employeeToAdd.getFirstName(), employeeToAdd.getLastName()));
		assertTrue(employeeToAdd.getID()!=null);
		assertEquals(null, project.getEmployee(employeeToAdd));
		
		isManager.setID(timeManagement.createID(isManager.getFirstName(), isManager.getLastName()));
		project.setProjectManager(isManager, isManager);
		assertEquals(isManager, project.getProjectManager());
		
		int employeeListSize = project.listEmployees().size(); // size of the employeelist in the project
		int projectListSize = employeeToAdd.getProjectList().size(); //  size of the projectlist in the employee
		project.addEmployee(employeeToAdd, isManager);
		assertEquals(employeeListSize + 1, project.listEmployees().size());
		assertEquals(projectListSize + 1, employeeToAdd.getProjectList().size());
	}
	
	@Test // C2
	void employeeAddedToProjectWhenAlreadyAddedToProject() throws Exception {
		Employee employeeToAdd = new Employee("AA", "BB", "CC");
		employeeToAdd.setID(timeManagement.createID(employeeToAdd.getFirstName(), employeeToAdd.getLastName()));
		assertTrue(employeeToAdd.getID()!=null);
		assertEquals(null, project.getEmployee(employeeToAdd));
		
		isManager.setID(timeManagement.createID(isManager.getFirstName(), isManager.getLastName()));
		project.setProjectManager(isManager, isManager);
		assertEquals(isManager, project.getProjectManager());
		
		project.addEmployee(employeeToAdd, isManager);
		
		try {
			project.addEmployee(employeeToAdd, isManager);
			fail("No exception thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
}