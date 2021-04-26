package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.Employee;
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
	private ActivityHelper activityHelper;

	public ProjectSteps(TimeManagement timeManagement,
						RegisterTime registerTime,
						ErrorHandler errorHandler,
						ProjectHelper projectHelper,
						EmployeeHelper employeeHelper,
						ActivityHelper activityHelper) {
		this.timeManagement = timeManagement;
		this.registerTime=registerTime;
		this.errorMessageHandler = errorHandler;
		this.projectHelper=projectHelper;
		this.employeeHelper=employeeHelper;
		this.activityHelper=activityHelper;
	}
	@Given("a project is in TimeManagement")
	public void aProjectIsInTimeManagement() throws Exception {
	    this.project= this.projectHelper.registerExampleProject();
	}
	@Given("an employee is registered as project manager of the project")
	public void anEmployeeIsRegisteredAsProjectManagerOfTheProject() {
	    timeManagement.setProjectManager(projectHelper.getProject(),this.employeeHelper.getEmployee());
	}

	@Given("the employee is not registered as project manager of the project")
	public void theEmployeeIsNotRegisteredAsProjectManagerOfTheProject() throws Exception {
		Employee manager = employeeHelper.getEmployee();
		Employee employee = employeeHelper.registerSecondExampleEmployee();
		timeManagement.setProjectManager(projectHelper.getProject(),manager);
		assertFalse(employee.getID()==timeManagement.getProject(projectHelper.getProject()).getProjectManager().getID());
	}
	@When("the employee adds another employee to the project")
	public void theEmployeeAddsAnotherEmployeeToTheProject() {
		Employee manager = employeeHelper.getEmployee();
		Employee employee = employeeHelper.getSecondEmployee();
		addEmployeehelp(manager,employee);
	}
	
	@Given("a project named {string} is created")
	public void aProjectNamedIsCreated(String name) {
		this.project= new Project(name);
	}
	@When("the project is added to TimeManagement")
	public void theProjectIsAddedToTimeManagement() {
		 try {
		    	timeManagement.createProject(project);
		    	
			} catch (OperationNotAllowedException e) {
				errorMessageHandler.setErrorMessage(e.getMessage());
			}
	}

	@Then("a project named {string} exists in TimeManagement")
	public void aProjectNamedExistsInTimeManagement(String name) {
		assertTrue(timeManagement.getProject(project).equals(project));
		assertTrue(name.equals(timeManagement.getProject(project).getName()));
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
	    assertFalse(employeeHelper.getSecondEmployee().getID() == timeManagement.getProject(projectHelper.getProject()).getProjectManager().getID());
	}
	@When("the project manager adds an employee to the project")
	public void theProjectManagerAddsAnEmployeeToTheProject() throws Exception {
	   Employee manager = employeeHelper.getEmployee();
	   Employee employee = employeeHelper.getSecondEmployee();
	   addEmployeehelp(employee,manager);
	}	
	public void addEmployeehelp(Employee employee1, Employee employee2) {
		try {
			timeManagement.addEmployeeToProject(employee1, projectHelper.getProject(),employee2);
		} catch (OperationNotAllowedException e) {
		
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
		
	
	@Then("the employee is added to the project")
	public void theEmployeeIsAddedToTheProject() {
	   assertTrue(employeeHelper.getSecondEmployee().equals(timeManagement.getEmployeeFromProject(employeeHelper.getSecondEmployee(), projectHelper.getProject())));
	}
	@When("the employee adds the activity to the project")
	public void theEmployeeAddsTheActivityToTheProject() {
	 try {
			timeManagement.addActivityToProject(activityHelper.getActivity(), project, employeeHelper.getEmployee());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity is added to the project")
	public void theActivityIsAddedToTheProject() {
		assertTrue(activityHelper.getActivity().equals(timeManagement.getProject(project).getActivity(activityHelper.getActivity())));
	}
	@When("the employee removes the activity from the project")
	public void theEmployeeRemovesTheActivityFromTheProject() {
		try {
			timeManagement.removeActivity(activityHelper.getActivity(),project,employeeHelper.getEmployee());
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the activity is not in the project")
	public void theActivityIsNotAddedToTheProject() {
		assertFalse(activityHelper.getActivity().equals(timeManagement.getProject(project).getActivity(activityHelper.getActivity())));

	}
}
