package timeManagement.Acceptance.Test;

import io.cucumber.java.en.When;
import timeManagement.ErrorHandler;
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
	
	public ActivitySteps(TimeManagement timeManagement,
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
	@When("a new activity with name {string} is created")
	public void aNewActivityWithNameIsCreated(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
