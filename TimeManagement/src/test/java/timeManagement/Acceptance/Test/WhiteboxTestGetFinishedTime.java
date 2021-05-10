package timeManagement.Acceptance.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import timeManagement.Activity;
import timeManagement.BegunActivity;
import timeManagement.DateServer;
import timeManagement.Employee;
import timeManagement.OperationNotAllowedException;
import timeManagement.RegisterTime;
import timeManagement.TimeManagement;

class WhiteboxTestGetFinishedTime {
	RegisterTime registerTimeTest = new RegisterTime();
	Activity activity = new Activity("Some activity");
	Employee employee = new Employee("Some first name", "Some last name", "email");
	DateServer dateServerTest = new DateServer();
	
	@Test //A1(1)
	void nullActivityTest() throws Exception {
		try {
			registerTimeTest.setFinishedTime(null, employee, null); // date value should not matter in this case
			fail("Exception not thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
	
	@Test //A1(2)
	void nullEmployeeTest() throws Exception {
		try {
			registerTimeTest.setFinishedTime(activity, null, null); // date value should not matter in this case
			fail("Exception not thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
	
	@Test //A3
	void nullDateTest() throws Exception {
		registerTimeTest.setBeginTime(activity, employee, null); // adds them to a begunActivity in registertime
		try {
			registerTimeTest.setFinishedTime(activity, employee, null);
			fail("Exception not thrown!");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test //B1
	void activityAndEmployeeInBegunActivity() throws Exception {
		int timeSpent = activity.getTimeSpent();
		registerTimeTest.setBeginTime(activity, employee, dateServerTest.getTime().getTime()); // adds them to a begunActivity in registertime
		int amountOfBegunActivities = registerTimeTest.getAmountOfBegunActivities();
		
		registerTimeTest.setFinishedTime(activity, employee, dateServerTest.getTime().getTime());
		int diffInMinutes = registerTimeTest.getDiffInMinutes();
		assertEquals(timeSpent + diffInMinutes, activity.getTimeSpent());
		assertEquals(amountOfBegunActivities-1, registerTimeTest.getAmountOfBegunActivities());
	}
	
	@Test // B2
	void activityAndEmployeeInDifferentBegunActivity() throws Exception {
		Activity otherActivity = new Activity("other activity");
		Employee otherEmployee = new Employee("AAAA", "BBBB", "email");
		registerTimeTest.setBeginTime(activity, otherEmployee, dateServerTest.getTime().getTime()); // adds them to a begunActivity in registertime
		registerTimeTest.setBeginTime(otherActivity, employee, dateServerTest.getTime().getTime()); // adds them to a begunActivity in registertime
		try {
			registerTimeTest.setFinishedTime(activity, employee, null); // dateserver value doesn't matter in this case
			fail("Exception not thrown!");
		} catch (OperationNotAllowedException e) {
			assertTrue(true);
		}
	}
}
