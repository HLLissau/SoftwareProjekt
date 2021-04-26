package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeManagement.Activity;
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
	public void anActivityWithTheNameIsInTimeManagement(String name) {
		aNewActivityWithNameIsCreated(name);
	}
	
	@When("a new activity with name {string} is created")
	public void aNewActivityWithNameIsCreated(String name) {
		try {
			activity=this.activityHelper.registerExampleActivity(name, projectHelper.getProject(), employeeHelper.getEmployee());
		} catch (Exception e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the employee sets the time of the activity to {int}")
	public void theProjectManagerSetsTheTimeOfTheActivityTo(int time) {
	    try {
			timeManagement.getProject(projectHelper.getProject()).setActivityTime(employeeHelper.getEmployee(), activityHelper.getActivity(), time);
		} catch (OperationNotAllowedException e) {
			errorMessageHandler.setErrorMessage(e.getMessage());
		}
	}
	@When("the second employee sets the time of the activity to {int}")
	public void theSecondEmployeeSetsTheTimeOfTheActivityTo(Integer time) {
		 try {
				timeManagement.getProject(projectHelper.getProject()).setActivityTime(employeeHelper.getSecondEmployee(), activityHelper.getActivity(), time);
			} catch (OperationNotAllowedException e) {
				errorMessageHandler.setErrorMessage(e.getMessage());
			}
	}
	
	@Then("a activity with the name {string} is in TimeManagement")
	public void aActivityWithTheNameIsInTimeManagement(String string) {
	    assertTrue(activity.equals(timeManagement.getProject(projectHelper.getProject()).getActivity(activity)));
	}
	
	@Then("a activity with the name {string} is not in TimeManagement")
	public void aActivityWithTheNameIsNotInTimeManagement(String string) {
	    assertFalse(activityHelper.getActivity().equals(timeManagement.getProject(projectHelper.getProject()).getActivity(activity)));
	}
	
	@Then("the time of the activity is set to {int}")
	public void theTimeOfTheActivityIsSetTo(Integer time) {
		assertTrue(time.equals(timeManagement.getProject(projectHelper.getProject()).getActivity(activityHelper.getActivity()).getTime()));
	}
	
	@Then("the time of the activity is not set to {int}")
	public void theTimeOfTheActivityIsNotSetTo(Integer time) {
	    assertFalse(time.equals(timeManagement.getProject(projectHelper.getProject()).getActivity(activityHelper.getActivity()).getTime()));
	}
}
