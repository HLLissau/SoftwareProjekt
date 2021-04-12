package timeManagement.Acceptance.Test;

import static org.junit.Assert.assertTrue;

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
	private ErrorHandler errorHandler;

	public ProjectSteps(TimeManagement timeManagement,
						RegisterTime registerTime,
						ErrorHandler errorHandler) {
		this.timeManagement = timeManagement;
		this.registerTime=registerTime;
		this.errorHandler = errorHandler;
	}
	
	@When("a project named {string} is created")
	public void aProjectNamedIsCreated(String name) {
	    this.project= new Project(name);
	    project.setID(timeManagement.createProjectID(registerTime.getYear()));
	    try {
	    	timeManagement.createProject(project);
	    	
		} catch (OperationNotAllowedException e) {
			errorHandler.setErrorMessage(e.getMessage());
		}
	    
	}

	@Then("a project named {string} exists in TimeManagement")
	public void aProjectNamedExistsInTimeManagement(String name) {
		assertTrue(timeManagement.getProject(project).equals(project));
	}
}
