package timeManagement.Acceptance.Test;
/*
 * Made by : Harald
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

public class ActivitySteps {
	private TimeManagement timeManagement;
	
	private ErrorHandler errorMessageHandler;
	private ProjectHelper projectHelper;
	private EmployeeHelper employeeHelper;
	private ActivityHelper activityHelper;
	private Activity activity;
	private Activity secondActivity;
	private Date begin;
	private Date finished;
	private int time;
	
	public ActivitySteps(TimeManagement timeManagement,
			RegisterTime registerTime,
			ErrorHandler errorHandler,
			ProjectHelper projectHelper,
			EmployeeHelper employeeHelper,
			ActivityHelper activityHelper) {
		this.timeManagement = timeManagement;
		this.errorMessageHandler = errorHandler;
		this.projectHelper=projectHelper;
		this.employeeHelper=employeeHelper;
		this.activityHelper=activityHelper;
	}
	
	@Given("an activity with the name {string} is in TimeManagement")
	public void anActivityWithTheNameIsInTimeManagement(String name) throws Exception {
		this.activity=activityHelper.registerExampleActivity(name);
	}
	
	@When("a new activity with name {string} is created")
	public void aNewActivityWithNameIsCreated(String name) {
		activity= new Activity(name);
		assertEquals(name, activity.getName());
	}
	
	@When("another activity with name {string} is created")
	public void anotherActivityWithNameIsCreated(String name) {
	    secondActivity = new Activity(name);
	    assertEquals(name, secondActivity.getName());
	}
	
	@When("the employee adds the activity to timeManagement")
	public void theEmployeeAddsTheActivityToTimeManagement() {
		try {
			timeManagement.createActivity(activity);
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the employee adds the activities to timeManagement")
	public void theEmployeeAddsTheActivitiesToTimeManagement() {
		try {
			timeManagement.createActivity(activity);
			timeManagement.createActivity(secondActivity);
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the employee sets the time of the activity to {int}")
	public void theProjectManagerSetsTheTimeOfTheActivityTo(int time) {
	    try {
			timeManagement.getProject(projectHelper.getProject().getID()).setActivityTime(employeeHelper.getEmployee(), activityHelper.getActivity().getID(), time);
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	
	@Then("a activity with the name {string} is in TimeManagement")
	public void aActivityWithTheNameIsInTimeManagement(String name) {
		assertTrue(activity.equals(timeManagement.getActivity(activity.getID())));
		assertTrue(name.equals(timeManagement.getActivity(activity.getID()).getName()));
	}
	
	
	@Then("the time of the activity is set to {int}")
	public void theTimeOfTheActivityIsSetTo(int time) {
		assertEquals(time,(timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).getTimeRemaining()));
	}
	
	@Then("the activities has unique IDs")
	public void theActivityHasAUniqueId() {
	    assertEquals(1, timeManagement.amountOfActivitiesWithID(activity.getID()));
	    assertEquals(1, timeManagement.amountOfActivitiesWithID(secondActivity.getID()));
	}
	
	@Then("the time of the activity is not set to {int}")
	public void theTimeOfTheActivityIsNotSetTo(int time) {
		assertNotEquals(time,(timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).getTimeRemaining()));
	}
	@Given("a activity is in the project")
	public void aActivityIsInTheProject() throws Exception {
		anActivityWithTheNameIsInTimeManagement("new activity");
		timeManagement.addActivityToProject(activityHelper.getActivity(), projectHelper.getProject().getID(), employeeHelper.getEmployee().getID());
		
	}
	@When("the project manager adds an employee to an activity")
	public void theProjectManagerAddsAnEmployeeToAnActivity() {
		addEmployeeToActivity(employeeHelper.getSecondEmployee(),employeeHelper.getEmployee());
	}
	@Then("the employee is added to the activity")
	public void theEmployeeIsAddedToTheActivity() {
		ArrayList<Employee> activitylist =timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).listEmployees();
		
		assertTrue(activitylist.contains(employeeHelper.getSecondEmployee()));
	}
	@When("the second employee adds an employee to an activity")
	public void theSecondEmployeeAddsAnEmployeeToAnActivity() {
		 addEmployeeToActivity(employeeHelper.getEmployee(),employeeHelper.getSecondEmployee());
	}
	
	public void addEmployeeToActivity(Employee employee, Employee manager) {
		try {
		  	timeManagement.addEmployeeToActivity(employee.getID(), projectHelper.getProject().getID(), activityHelper.getActivity().getID(), manager.getID());
		} catch (Exception e) {
			
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@When("the employee begin work on the activity")
	public void theEmployeeBeginWorkOnTheActivity() {
	    
		try {
			timeManagement.beginWorkOnActivity(employeeHelper.getSecondEmployee().getID(),activityHelper.getActivity().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	} 
	@Then("the begin time is set and the begun activity is in begun activity List")
	public void theBeginTimeIsSetAndTheBegunActivityIsInBegunActivityList() {
	
	    timeManagement.adminLogin("adminadmin");
		try {
			 begin  = timeManagement.getRegisterTime().getBeginTimeOfActivityByEmployee(activityHelper.getActivity(),employeeHelper.getSecondEmployee());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
		assertFalse(begin.equals(null));
	}
	
	@When("the employee end work on the activity")
	public void theEmployeeEndWorkOnTheActivity() {	
		try {
			finished = timeManagement.stopWorkOnActivity(employeeHelper.getSecondEmployee().getID(),activityHelper.getActivity().getID());
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity consumed time is increased by {int}")
	public void theActivityConsumedTimeIsIncreasedBy(int amount) throws OperationNotAllowedException {
		
		assertEquals(this.time,amount);
	}
	@When("the Employee check the time of the activity")
	public void theEmployeeCheckTheTimeOfTheActivity() {
		try {
			 this.time =timeManagement.getTimeSpentOnActivity(activityHelper.getActivity().getID());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@When("the project manager removes the employee from the activity")
	public void theProjectManagerRemovesTheEmployeeFromTheActivity() {
		removeEmployeeFromActivity(employeeHelper.getSecondEmployee(),employeeHelper.getEmployee());
	}

	private void removeEmployeeFromActivity(Employee employee, Employee manager) {
		try {
			timeManagement.removeEmployeeFromActivity(employee.getID(), activityHelper.getActivity().getID());
		} catch (Exception e) {
			
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee is removed from the activity")
	public void theEmployeeIsRemovedFromTheActivity() {
		ArrayList<Employee> activitylist =timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).listEmployees();
		
		assertFalse(activitylist.contains(employeeHelper.getSecondEmployee()));
	}
	@When("want to get activity")
	public void wantToGetActivity() {
	    activity =this.timeManagement.getActivity(activityHelper.getActivity().getID());
	}

	@Then("no activity is returned")
	public void noActivityIsReturned() {
	   assertNull(activity);
	}
	
}
