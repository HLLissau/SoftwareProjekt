package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.Employee;
import timeManagement.ErrorHandler;
import timeManagement.OperationNotAllowedException;
import timeManagement.TimeManagement;

public class LoginLogoutSteps {
	private TimeManagement timeManagement;
	private Employee employee;
	private int id;
	private ErrorHandler errorMessageHandler;
	private EmployeeHelper employeeHelper;

	public LoginLogoutSteps(TimeManagement timeManagement,
							ErrorHandler errorHandler,
							EmployeeHelper employeeHelper) {
		this.timeManagement= timeManagement;
		this.errorMessageHandler = errorHandler;
		this.employeeHelper = employeeHelper;
	
	}
	@Given("that the administrator is logged in")
	public void thatTheAdministratorIsLoggedIn() { 
	   timeManagement.adminLogin("adminadmin");
	   assertTrue(timeManagement.adminLoggedIn());
	}

	@Given("there is a user with first name {string} , last name {string} , email {string}")
	public void thereIsAUserWithFirstNameLastNameEmail(String firstName, String lastName, String email) {
		this.employee=new Employee(firstName, lastName, email);
		
	}

	@When("the administrator registers the employee in TimeManagement")
	public void theAdministratorRegistersTheEmployeeInTimeManagement() throws Exception {
			try {
				timeManagement.createEmployee(employee);
			} catch (OperationNotAllowedException e) {
				errorMessageHandler.setErrorMessage(e.getMessage());
			}
		
		
	}

	@Then("the employee is registered in TimeManagement")
	public void theEmployeeIsRegisteredInTimeManagement() {
	    assertTrue(employee.equals(timeManagement.getEmployee(employee.getID())));
	}


	@Then("the employee is given a unique id")
	public void theEmployeeIsGivenAUniqueId() {
		//must return false, because the employee is in the system.
		assertFalse(timeManagement.isUniqueEmployeeID(employee.getID()));
		
	}
	@Given("that the administrator is not logged in")
	public void thatTheAdministratorIsNotLoggedIn() {
	    thatTheAdministratorIsLoggedIn();
	    timeManagement.adminlogout();
	    assertFalse(timeManagement.adminLoggedIn());
	}


	@Then("the error message {string} is given")
	public void theErrorMessageIsGiven(String string) {
	   assertTrue(string.equals(errorMessageHandler.getErrorMessage()));
	}
	@Given("an employee is registered with TimeManagement")
	public void anEmployeeIsRegisteredWithTimeManagement() throws Exception {
		this.employee= this.employeeHelper.registerExampleEmployee();
		
	}

	@When("another employee is logged in")
	public void anotherEmployeeIsLoggedIn() throws Exception {
	    this.employeeHelper.registerNewExampleEmployee();
		
	}
	@When("the administrator deletes the employee from TimeManagement")
	public void theAdministratorDeletesTheEmployeeFromTimeManagement() {
	    try {
	    	timeManagement.removeEmployeeFromTimeManagement(employeeHelper.getEmployee().getID());
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee is not registered in TimeManagement")
	public void theEmployeeIsNotRegisteredInTimeManagement() {
		assertFalse(employeeHelper.getEmployee().equals(timeManagement.getEmployee(employeeHelper.getEmployee().getID())));
	}
	@Then("the employee is still registered with TimeManagement")
	public void theEmployeeIsStillRegisteredWithTimeManagement() {
		assertTrue(employeeHelper.getEmployee().equals(timeManagement.getEmployee(employeeHelper.getEmployee().getID())));
	}

}
