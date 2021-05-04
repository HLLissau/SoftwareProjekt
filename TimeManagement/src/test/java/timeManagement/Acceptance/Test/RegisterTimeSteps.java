package timeManagement.Acceptance.Test;

import io.cucumber.java.en.Given;

public class RegisterTimeSteps {
	private MockDateHolder dateHolder;
	
	public RegisterTimeSteps (MockDateHolder dateHolder) {
		this.dateHolder = dateHolder;
	}
			

	@Given("{int} minutes have passed")
	public void daysHavePassed(int minutes) throws Exception {
		dateHolder.advanceTimeByMinutes(minutes);
	}
}