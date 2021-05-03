package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
	private ArrayList<Employee> avaiableList;
	private int listSize;

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
	    try {
			timeManagement.setProjectManager(projectHelper.getProject().getID(),this.employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Given("the employee is not registered as project manager of the project")
	public void theEmployeeIsNotRegisteredAsProjectManagerOfTheProject() throws Exception {
		Employee employee = employeeHelper.getEmployee();
		Employee manager = employeeHelper.registerSecondExampleEmployee();
		timeManagement.setProjectManager(projectHelper.getProject().getID(),manager.getID());
		assertFalse(employee.getID()==timeManagement.getProject(projectHelper.getProject().getID()).getProjectManager().getID());
	}
	@When("the employee adds another employee to the project")
	public void theEmployeeAddsAnotherEmployeeToTheProject() {
		Employee employee = employeeHelper.getEmployee();
		Employee manager = employeeHelper.getSecondEmployee();
		addEmployeehelp(manager,employee);
	}
	
	@Given("a project named {string} is created")
	public void aProjectNamedIsCreated(String name) {
		this.project= projectHelper.getProject();
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
		assertTrue(timeManagement.getProject(projectHelper.getProject().getID()).equals(project));
		assertTrue(name.equals(timeManagement.getProject(project.getID()).getName()));
	}
	@Then("a project named {string} does not exist in TimeManagement")
	public void aProjectNamedDoesNotExistInTimeManagement(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(project.equals(timeManagement.getProject(project.getID())));
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
	    assertFalse(employeeHelper.getSecondEmployee().getID() == timeManagement.getProject(projectHelper.getProject().getID()).getProjectManager().getID());
	}
	@When("the project manager adds an employee to the project")
	public void theProjectManagerAddsAnEmployeeToTheProject() throws Exception {
	   Employee manager = employeeHelper.getEmployee();
	   Employee employee = employeeHelper.getSecondEmployee();
	   addEmployeehelp(employee,manager);
	}	
	@Given("a second employee is registered with TimeManagement")
	public void aSecondEmployeeIsRegisteredWithTimeManagement() {
		try {
			employeeHelper.registerSecondExampleEmployee();
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
			
		}
	}
	public void addEmployeehelp(Employee employee1, Employee employee2) {
		try {
			timeManagement.addEmployeeToProject(employee1.getID(), projectHelper.getProject().getID(),employee2.getID());
		} catch (OperationNotAllowedException e) {
		
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
		
	
	@Then("the employee is added to the project")
	public void theEmployeeIsAddedToTheProject() {
	   assertTrue(employeeHelper.getSecondEmployee().equals(timeManagement.getEmployeeFromProject(employeeHelper.getSecondEmployee().getID(), projectHelper.getProject().getID())));
	}
	@When("the employee adds the activity to the project")
	public void theEmployeeAddsTheActivityToTheProject() {
	 try {
			timeManagement.addActivityToProject(activityHelper.getActivity(), project.getID(), employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity is added to the project")
	public void theActivityIsAddedToTheProject() {
		assertTrue(activityHelper.getActivity().equals(timeManagement.getProject(project.getID()).getActivity(activityHelper.getActivity().getID())));
	}
	@When("the employee removes the activity from the project")
	public void theEmployeeRemovesTheActivityFromTheProject() {
		try {
			timeManagement.removeActivity(activityHelper.getActivity().getID(),project.getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the activity is not in the project")
	public void theActivityIsNotAddedToTheProject() {
		assertFalse(activityHelper.getActivity().equals(timeManagement.getProject(project.getID()).getActivity(activityHelper.getActivity().getID())));

	}
	@When("the project manager removes the employee from the project")
	public void theProjectManagerRemovesTheEmployeeFromTheProject() {
	    try {
			timeManagement.removeEmployeeFromProject(employeeHelper.getEmployee().getID(),projectHelper.getProject().getID(),employeeHelper.getSecondEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee is no longer in the project")
	public void theEmployeeIsNoLongerInTheProject() {
	    assertFalse(employeeHelper.getEmployee().equals(timeManagement.getProject(projectHelper.getProject().getID()).getEmployee(employeeHelper.getEmployee())));
	}
	@When("the project manager edits the description to {string}")
	public void theProjectManagerEditsTheDescriptionTo(String description) {
	    try {
			timeManagement.setProjectDescription(description,projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@Then("the project description is {string}")
	public void theProjectDescriptionIs(String description) {
	    try {
			assertTrue(description.equals(timeManagement.getProjectDescription(project.getID())));
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}    		
	}
	@When("the project manager sets the project time to {int}")
	public void theProjectManagerSetsTheProjectTimeTo(Integer time) {
    try {
			timeManagement.setTimeOfProject(time,projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity time is set to {int}")
	public void theActivityTimeIsSetTo(int time) {
		try {
			assertTrue(time ==(timeManagement.getProjectTime(project.getID())));
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@When("the employee is registered as project manager")
	public void theEmployeeIsRegisteredAsProjectManager() {
		anEmployeeIsRegisteredAsProjectManagerOfTheProject();
	}
	@Then("the employee is registered as the project manager")
	public void theEmployeeIsRegisteredAsTheProjectManager() {
	  assertTrue(employeeHelper.getEmployee().equals(timeManagement.getProject(projectHelper.getProject().getID()).getProjectManager()));
	}
	@When("the Employee is unregistered as project manager")
	public void theEmployeeIsUnregisteredAsProjectManager() {
	    try {
			timeManagement.removeProjectManagerFromProject(projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
			
		}
	}
	@Then("the Employee is no longer project manager")
	public void theEmployeeIsNoLongerProjectManager() {
	    assertFalse(employeeHelper.getEmployee().equals(timeManagement.getProject(projectHelper.getProject().getID()).getProjectManager()));
	}
	@Given("a project exists and a project manager is logged in")
	public void aProjectExistsAndAProjectManagerIsLoggedIn() throws Exception {
		employeeHelper.registerExampleEmployee();
		aProjectIsInTimeManagement();
		anEmployeeIsRegisteredAsProjectManagerOfTheProject();
	}

	@When("the project manager requests a list of employees with under {int} activitties")
	public void theProjectManagerRequestsAListOfEmployeesWithUnderActivitties(int number) {
	    this.avaiableList = timeManagement.listAvailableEmployees(number);
	}
	@Then("system returns a list of  employees with less than {int} activities.")
	public void systemReturnsAListOfEmployeesWithLessThanActivities(Integer int1) {
	    boolean checkint = true;
		for (Employee e : this.avaiableList) {
	    	if (e.canBeAssigned() > int1) {
	    		checkint=false;
	    	}
	    }
		assertTrue(checkint);
	}
	@Given("a project have {int} employees added")
	public void aProjectHaveEmployeesAdded(int int1) throws Exception {
	    this.listSize= timeManagement.listEmployeesOnProject(projectHelper.getProject().getID()).size();
		for (int i = 0; i<int1; i++) {
	    	Employee e =employeeHelper.registerNewSecondExampleEmployee();
	    	timeManagement.addEmployeeToProject(e.getID(), projectHelper.getProject().getID(), employeeHelper.getEmployee().getID());
	    }
	}
	@When("the project manager requests  a list of employees on the specific project")
	public void theProjectManagerRequestsAListOfEmployeesOnTheSpecificProject() {
	    this.avaiableList = timeManagement.listEmployeesOnProject(projectHelper.getProject().getID());
	}
	@Then("system returns a list of employees with the {int} added employees")
	public void systemReturnsAListOfEmployeesWithTheAddedEmployees(int int1) {
		assertTrue(this.listSize +int1 ==timeManagement.listEmployeesOnProject(projectHelper.getProject().getID()).size());
	}

	
}
