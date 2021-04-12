package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.Employee;
import timeManagement.TimeManagement;

public class LoginLogoutSteps {
	private TimeManagement timeManagement;
	private Employee employee;
	private int id;

	public LoginLogoutSteps() {
		this.timeManagement= new TimeManagement();
	}
	@Given("that the administrator is logged in")
	public void thatTheAdministratorIsLoggedIn() {
	   timeManagement.adminLogin("adminadmin");
	   assertTrue(timeManagement.adminLoggedIn());
	}

	@Given("there is a user with name {string}, email {string}")
	public void thereIsAUserWithNameEmail(String name, String email) {
		this.employee=new Employee(name,email);
	}

	@When("the administrator registers the employee in TimeManagement")
	public void theAdministratorRegistersTheEmployeeInTimeManagement() {
		 timeManagement.createEmployee(employee);
	}

	@Then("the employee is registered in TimeManagement")
	public void theEmployeeIsRegisteredInTimeManagement() {
	    assertTrue(timeManagement.getEmployee(employee).equals(employee));
	}


	@Then("the employee is given a unique id")
	public void theEmployeeIsGivenAUniqueId() {
		
	}
}
