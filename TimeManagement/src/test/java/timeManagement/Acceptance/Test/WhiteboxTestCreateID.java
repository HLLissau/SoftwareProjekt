package timeManagement.Acceptance.Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import timeManagement.Employee;
import timeManagement.OperationNotAllowedException;
import timeManagement.TimeManagement;

class WhiteboxTestCreateID {
	TimeManagement timeManagement = new TimeManagement();
	EmployeeHelper employeeHelper = new EmployeeHelper(timeManagement);
	
	@Test //A1
	void ifInputIsNull() {
		String id;
		try {
			id = timeManagement.createID(null, null);
			fail("Exception not thrown!");
		} catch (Exception e) {
			assertTrue(true); // IF an exception is thrown, our test pass
		}
	}
	
	@Test //A2
	void ifInputIsEmpty() {
		try {
			String id = timeManagement.createID("", "");
			fail("Exception not thrown!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test //C1
	void ifIdNotInTimeManagement() throws Exception {
		// example employee with firstName "Jens" lastName "Hansen"
		// should result in "JEHA" id when there is no other employee with that id already
		Employee test_employee = employeeHelper.getEmployee();
		String id = timeManagement.createID(test_employee.getFirstName(), test_employee.getLastName());
		assertEquals("JEHA", id);
	}
	
	@Test //C2
	void ifIdIsInTimeManagement() throws Exception {
		// example employee with firstName "Jens" lastName "Hansen"
		// should result in "AAAA" id when there is an employee with that id already
		Employee test_employee = employeeHelper.getEmployee();
		Employee registered_employee = employeeHelper.registerExampleEmployee();
		String id = timeManagement.createID(test_employee.getFirstName(), test_employee.getLastName());
		assertEquals("AAAA", id);
	}
	
	@Test //C3
	void ifIdIsInTimeManagement2() throws Exception {
		// example employee with firstName "Jens" lastName "Hansen"
		// should result in "AAAB" id when there is an employee with that ID and ID "AAAA" already
		Employee test_employee = employeeHelper.getEmployee();
		Employee registered_employee_1 = employeeHelper.registerExampleEmployee();
		Employee registered_employee_2 = employeeHelper.registerExampleEmployee(new Employee("AA", "AA", "email"));
		
		// check that 1st has ID "JEHA" and 2nd ID "AAAA"
		assertEquals("JEHA", registered_employee_1.getID());
		assertEquals("AAAA", registered_employee_2.getID());
		
		String id = timeManagement.createID(test_employee.getFirstName(), test_employee.getLastName());
		assertEquals("AAAB", id);
	}
	
	@Test //C4
	void ifIdIsInTimeManagement3() throws Exception {
		// example employee with firstName "Jens" lastName "Hansen"
		// should result in "AABA" id when all IDs AAAX is used up (where X = [A, B,...,Z])
		Employee test_employee = employeeHelper.getEmployee();
		Employee registered_employee_1 = employeeHelper.registerExampleEmployee();
		
		// Create employees with ID "AAAX"
		for (char c0 = 'A'; c0 <= 'Z'; c0++) {
			Employee registered_employee_2 = employeeHelper.registerExampleEmployee(new Employee("AA", "A" + c0, "email"));
			assertEquals("AAA" + c0, registered_employee_2.getID());
		}
		String id = timeManagement.createID(test_employee.getFirstName(), test_employee.getLastName());
		assertEquals("AABA", id);		
	}
	
	@Test //C4
	void ifIdIsInTimeManagement4() throws Exception {
		// example employee with firstName "Jens" lastName "Hansen"
		// should result in "AABA" id when all IDs AXXX is used up (where X = [A, B,...,Z])
		Employee test_employee = employeeHelper.getEmployee();
		Employee registered_employee_1 = employeeHelper.registerExampleEmployee();
		
		// Create employees with ID "AXXX"
		char c0 = 'A';
		for (char c1 = 'A'; c1 <= 'Z'; c1++) {
			for (char c2 = 'A'; c2 <= 'Z'; c2++) {
				for (char c3 = 'A'; c3 <= 'Z'; c3++) {
					Employee registered_employee_2 = employeeHelper.registerExampleEmployee(new Employee("" + c0 + c1, "" + c2 + c3, "email"));
					assertEquals("" + c0 + c1 + c2 + c3, registered_employee_2.getID());
				}
			}
		}
		String id = timeManagement.createID(test_employee.getFirstName(), test_employee.getLastName());
		assertEquals("BAAA", id);	
	}
}
