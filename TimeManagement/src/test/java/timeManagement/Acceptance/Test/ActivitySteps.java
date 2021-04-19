package timeManagement.Acceptance.Test;

import io.cucumber.java.en.When;
import timeManagement.Activity;
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
	@When("a new activity with name {string} is created")
	public void aNewActivityWithNameIsCreated(String string) throws Exception {
	    activity=this.activityHelper.registerExampleActivity();
	}
	
}
