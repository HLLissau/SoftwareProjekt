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

	public LoginLogoutSteps() {
		this.timeManagement= new TimeManagement();
		this.errorMessageHandler = new ErrorHandler();
		this.employeeHelper = new EmployeeHelper(timeManagement);
	
	}
	@Given("that the administrator is logged in")
	public void thatTheAdministratorIsLoggedIn() {
	   timeManagement.adminLogin("adminadmin");
	   assertTrue(timeManagement.adminLoggedIn());
	}

	@Given("there is a user with name {string}, email {string}")
	public void thereIsAUserWithNameEmail(String name, String email) {
		this.employee=new Employee(name,email);
		employee.setID(timeManagement.createID());
	}

	@When("the administrator registers the employee in TimeManagement")
	public void theAdministratorRegistersTheEmployeeInTimeManagement() {
			try {
				timeManagement.addEmployee(employee);
			} catch (OperationNotAllowedException e) {
				errorMessageHandler.setErrorMessage(e.getMessage());
			}
		
		
	}

	@Then("the employee is registered in TimeManagement")
	public void theEmployeeIsRegisteredInTimeManagement() {
	    assertTrue(timeManagement.getEmployee(employee).equals(employee));
	}


	@Then("the employee is given a unique id")
	public void theEmployeeIsGivenAUniqueId() {
		assertFalse(timeManagement.isUniqueID(employee.getID()));
		
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
	public void anEmployeeIsRegisteredWithTimeManagement() {
		
		this.employee= this.employeeHelper.getEmployee();
		
	}
	@When("the administrator registers the employee again")
	public void theAdministratorRegistersTheEmployeeAgain() {
	    try {
			this.timeManagement.addEmployee(employee);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
}
