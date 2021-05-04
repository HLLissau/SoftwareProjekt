package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
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
	private Project project;
	private RegisterTime registerTime;
	private ErrorHandler errorMessageHandler;
	private ProjectHelper projectHelper;
	private EmployeeHelper employeeHelper;
	private ActivityHelper activityHelper;
	private Activity activity;
	private Date begin;
	private Date finished;
	
	public ActivitySteps(TimeManagement timeManagement,
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
	
	@Given("an activity with the name {string} is in TimeManagement")
	public void anActivityWithTheNameIsInTimeManagement(String name) throws Exception {
		this.activity=activityHelper.registerExampleActivity(name);
	}
	
	@When("a new activity with name {string} is created")
	public void aNewActivityWithNameIsCreated(String name) {
		activity= new Activity(name);
		
		
	}
	@When("the employee adds the activity to timeManagement")
	public void theEmployeeAddsTheActivityToTimeManagement() {
		try {
			timeManagement.createActivity(activity);
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
	
//	@Then("a activity with the name {string} is not in TimeManagement")
//	public void aActivityWithTheNameIsNotInTimeManagement(String string) {
//	    assertFalse(activityHelper.getActivity().equals(timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activity.getID())));
//	}
	
	@Then("the time of the activity is set to {int}")
	public void theTimeOfTheActivityIsSetTo(int time) {
		assertTrue(time ==(timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).getTimeRemaining()));
	}
	
	@Then("the time of the activity is not set to {int}")
	public void theTimeOfTheActivityIsNotSetTo(int time) {
	    assertFalse(time ==(timeManagement.getProject(projectHelper.getProject().getID()).getActivity(activityHelper.getActivity().getID()).getTimeRemaining()));
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
	@Then("the begin time is set")
	public void theBeginTimeIsSet() {
		
	    timeManagement.adminLogin("adminadmin");
		try {
			 begin  = timeManagement.getRegisterTime().getBeginTimeOfActivityByEmployee(activityHelper.getActivity(),employeeHelper.getSecondEmployee());
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
		assertTrue(!begin.equals(null));
	}
	
	@When("the employee end work on the activity")
	public void theEmployeeEndWorkOnTheActivity() {
						
		try {
			timeManagement.stopWorkOnActivity(employeeHelper.getSecondEmployee().getID(),activityHelper.getActivity().getID());
			
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
		
	}

	@Then("the activity consumed time is increased by {int}")
	public void theActivityConsumedTimeIsIncreasedBy(int amount) {
		
		System.out.println(begin);
		System.out.println(finished);
	}

}
