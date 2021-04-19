package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.ErrorHandler;
import timeManagement.OperationNotAllowedException;
import timeManagement.Project;
import timeManagement.RegisterTime;
import timeManagement.TimeManagement;

public class ProjectSteps {
	private TimeManagement timeManagement;
	private Project project;
	private RegisterTime registerTime;
	private ErrorHandler errorMessageHandler;
	private ProjectHelper projectHelper;
	private EmployeeHelper employeeHelper;

	public ProjectSteps(TimeManagement timeManagement,
						RegisterTime registerTime,
						ErrorHandler errorHandler,
						ProjectHelper projectHelper,
						EmployeeHelper employeeHelper) {
		this.timeManagement = timeManagement;
		this.registerTime=registerTime;
		this.errorMessageHandler = errorHandler;
		this.projectHelper=projectHelper;
		this.employeeHelper=employeeHelper;
	}
	@Given("a project is in TimeManagement")
	public void aProjectIsInTimeManagement() throws Exception {
	    this.project= this.projectHelper.registerExampleProject();
	}
	@Given("an employee is registered as project manager of the project")
	public void anEmployeeIsRegisteredAsProjectManagerOfTheProject() {
	    timeManagement.getProject(project).setProjectManager(this.employeeHelper.getEmployee());
	}

	
	
	@When("a project named {string} is created")
	public void aProjectNamedIsCreated(String name) {
	    this.project= new Project(name);
	    project.setID(timeManagement.createProjectID(registerTime.getYear()));
	    try {
	    	timeManagement.createProject(project);
	    	
		} catch (OperationNotAllowedException e) {
			//System.out.print("fejl");
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	    
	}

	@Then("a project named {string} exists in TimeManagement")
	public void aProjectNamedExistsInTimeManagement(String name) {
		assertTrue(timeManagement.getProject(project).equals(project));
	}
	@Then("a project named {string} does not exist in TimeManagement")
	public void aProjectNamedDoesNotExistInTimeManagement(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(project.equals(timeManagement.getProject(project)));
	}
	
	@Then("an ID is assigned to the project")
	public void anIDIsAssignedToTheProject() {
	   assertNotNull(project.getID());
	}

	@Then("no other project has the same ID")
	public void noOtherProjectHasTheSameID() {
		assertFalse(timeManagement.isUniqueProjectID(project.getID()));
	}
	
	@Given("an employee is not registered as project manager of the project")
	public void anEmployeeIsNotRegisteredAsProjectManagerOfTheProject() {
	    assertFalse(employeeHelper.getEmployee().equals(projectHelper.getProject().getProjectManager()));
	}
	
	@When("another employee is logged in who is not project manager")
	public void anotherEmployeeIsLoggedInWhoIsNotProjectManager() throws Exception {
	    employeeHelper.registerNewExampleEmployee();
	    assertFalse(employeeHelper.getEmployee().getID() == timeManagement.getProject(projectHelper.getProject()).getProjectManager().getID());
	}
}
