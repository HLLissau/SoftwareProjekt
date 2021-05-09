package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.Activity;
import timeManagement.Employee;
import timeManagement.ErrorHandler;
import timeManagement.OperationNotAllowedException;
import timeManagement.Project;
import timeManagement.RegisterTime;
import timeManagement.TimeManagement;

public class ProjectSteps {
	private TimeManagement timeManagement;
	private Project project;
	private Project secondProject;
	private RegisterTime registerTime;
	private ErrorHandler errorMessageHandler;
	private ProjectHelper projectHelper;
	private EmployeeHelper employeeHelper;
	private ActivityHelper activityHelper;
	private ArrayList<Employee> avaiableList = new ArrayList<Employee>();;
	private int listSize;
	private ArrayList<Project> projectlist;
	private ArrayList<Activity> activitylist;

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
	@When("the employee registers as project manager")
	public void theEmployeeRegistersAsProjectManager() {
    try {
			timeManagement.setProjectManager(projectHelper.getProject().getID(),this.employeeHelper.getEmployee().getID(),this.employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@When("the employee registers a new project manager")
	public void theEmployeeRegistersANewProjectManager() {
		
		try {
			timeManagement.setProjectManager(projectHelper.getProject().getID(),this.employeeHelper.getSecondEmployee().getID(),this.employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}  

	@Given("the employee is not registered as project manager of the project")
	public void theEmployeeIsNotRegisteredAsProjectManagerOfTheProject() throws Exception {
		Employee employee = employeeHelper.getEmployee();
		Employee manager = employeeHelper.registerSecondExampleEmployee();
		timeManagement.setProjectManager(projectHelper.getProject().getID(),manager.getID(),manager.getID());
		assertNotEquals(employee.getID(),timeManagement.getProject(projectHelper.getProject().getID()).getProjectManager().getID());
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
	
	@When("the projects are added to TimeManagement")
	public void theProjectsAreAddedToTimeManagement() {
		 try {
		    	timeManagement.createProject(project);
		    	timeManagement.createProject(secondProject);
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
	
	@Then("IDs are assigned to the projects")
	public void anIDIsAssignedToTheProject() {
	   assertNotNull(project.getID());
	   assertNotNull(secondProject.getID());
	}

	@Then("the projects don't have the same IDs")
	public void noOtherProjectHasTheSameID() {
		assertEquals(1, timeManagement.amountOfProjectsWithID(project.getID()));
		assertEquals(1, timeManagement.amountOfProjectsWithID(secondProject.getID()));
	}
	
	@Given("an employee is not registered as project manager of the project")
	public void anEmployeeIsNotRegisteredAsProjectManagerOfTheProject() {
	    assertFalse(employeeHelper.getEmployee().equals(projectHelper.getProject().getProjectManager()));
	}

	@When("the project manager adds an employee to the project")
	public void theProjectManagerAddsAnEmployeeToTheProject() {
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
		
	@Then("the project manager is added to the project")
	public void theProjectManagerIsAddedToTheProject() {
		assertTrue(employeeHelper.getEmployee().equals(timeManagement.getEmployeeFromProject(employeeHelper.getEmployee().getID(), projectHelper.getProject().getID())));
	}
	@Then("the employee is added to the project")
	public void theEmployeeIsAddedToTheProject() {
	   assertTrue(employeeHelper.getSecondEmployee().equals(timeManagement.getEmployeeFromProject(employeeHelper.getSecondEmployee().getID(), projectHelper.getProject().getID())));
	}
	@When("the employee adds the activity to the project")
	public void theEmployeeAddsTheActivityToTheProject() {
	 try {
			timeManagement.addActivityToProject(activityHelper.getActivity(), project.getID(), employeeHelper.getEmployee().getID());
		} catch (Exception e) {
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
		} catch (Exception e) {
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
			timeManagement.removeEmployeeFromProject(employeeHelper.getSecondEmployee().getID(),projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee is no longer in the project")
	public void theEmployeeIsNoLongerInTheProject() {
	    assertFalse(employeeHelper.getSecondEmployee().equals(timeManagement.getProject(projectHelper.getProject().getID()).getEmployee(employeeHelper.getSecondEmployee())));
	}
	@When("the employee edits the description to {string}")
	public void theEmployeeEditsTheDescriptionTo(String description) {
	    try {
			timeManagement.setProjectDescription(description,projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@Then("the project description is {string}")
	public void theProjectDescriptionIs(String description) {
	    
			assertTrue(description.equals(timeManagement.getProjectDescription(project.getID())));
		  		
	}
	@When("the employee sets the project time to {int}")
	public void theEmployeeSetsTheProjectTimeTo(Integer time) {
    try {
			timeManagement.setTimeOfProject(time,projectHelper.getProject().getID(),employeeHelper.getEmployee().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity time is set to {int}")
	public void theActivityTimeIsSetTo(int time) {
		assertEquals(time,(timeManagement.getProjectTime(project.getID())));
		
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
		theEmployeeRegistersAsProjectManager(); 
	}

	@When("the project manager requests a list of employees with under {int} activitties")
	public void theProjectManagerRequestsAListOfEmployeesWithUnderActivitties(int number) {
	    this.avaiableList = timeManagement.listAvailableEmployees(number);
	}
	@Then("system returns a list of  employees with less than {int} activities.")
	public void systemReturnsAListOfEmployeesWithLessThanActivities(Integer int1) {
		
		assertFalse(this.avaiableList.stream().filter(e-> e.canBeAssigned()>int1).findAny().isPresent());

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
		assertEquals(this.listSize +int1,timeManagement.listEmployeesOnProject(projectHelper.getProject().getID()).size());
	}
	@Then("the list of all projecs are returned.")
	public void theListOfAllProjecsAreReturned() {
	    assertTrue(projectlist.equals(timeManagement.getAllProjects()));
	}

	@When("the employee wants to get a list of all projecs in TimeManagement")
	public void theEmployeeWantsToGetAListOfAllProjecsInTimeManagement() {
	    projectlist = timeManagement.getAllProjects();
	}
	@Then("the list of all employees are returned.")
	public void theListOfAllEmployeesAreReturned() {
	    assertTrue(this.avaiableList.equals(timeManagement.getAllEmployees()));
	}
	@When("the employee wants to get a list of all employee in TimeManagement")
	public void theEmployeeWantsToGetAListOfAllEmployeeInTimeManagement() {
		this.avaiableList= timeManagement.getAllEmployees();
		
	}
	@When("the project manager wants to get a list of all activities in project")
	public void theProjectManagerWantsToGetAListOfAllActivitiesInProject() {
	    this.activitylist = timeManagement.getProject(projectHelper.getProject().getID()).getActivityList();
	}

	@Then("the list of all activities are returned")
	public void theListOfAllActivitiesAreReturned() {
	    assertTrue(activitylist.equals(timeManagement.getProject(projectHelper.getProject().getID()).getActivityList()));
	}
	@When("the project manager wants to get a list of all employees working on activity")
	public void theProjectManagerWantsToGetAListOfAllEmployeesWorkingOnActivity() {
	    this.avaiableList = timeManagement.getProject(projectHelper.getProject().getID()).getActivityList().get(0).getEmployeeList();
	}

	@Then("the list of all employees working on activity are returned")
	public void theListOfAllEmployeesWorkingOnActivityAreReturned() {
	    assertTrue(avaiableList.equals(timeManagement.getProject(projectHelper.getProject().getID()).getActivityList().get(0).getEmployeeList()));
	}

@When("the project manager wants to get a list of all activities an employee is working on")
public void theProjectManagerWantsToGetAListOfAllActivitiesAnEmployeeIsWorkingOn() {
	 this.activitylist = timeManagement.getEmployeeActivityList(employeeHelper.getSecondEmployee().getID());
}

@Then("the list of all all activities an employee is working on is returned")
public void theListOfAllAllActivitiesAnEmployeeIsWorkingOnIsReturned() {
    assertTrue(activitylist.equals(timeManagement.getEmployeeActivityList(employeeHelper.getSecondEmployee().getID())));
}

@When("the project manager wants to get a list of all projects an employee is working on")
public void theProjectManagerWantsToGetAListOfAllProjectsAnEmployeeIsWorkingOn() {
	this.projectlist = timeManagement.getEmployeeProjectList(employeeHelper.getSecondEmployee().getID());
}

@Then("the list of all all projects an employee is working on is returned")
public void theListOfAllAllProjectsAnEmployeeIsWorkingOnIsReturned() {
	 assertTrue(projectlist.equals(timeManagement.getEmployeeProjectList(employeeHelper.getSecondEmployee().getID())));
}

@Given("another project named {string} is created")
public void anotherProjectNamedIsCreated(String name) {
    this.secondProject = projectHelper.getSecondProject();
    assertEquals(name, secondProject.getName());
}
	
}
