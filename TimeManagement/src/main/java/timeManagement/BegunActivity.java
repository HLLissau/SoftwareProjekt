package timeManagement;
/*
 * Made by : Anton
 */
import java.util.Date;
import java.util.GregorianCalendar;

public class BegunActivity {
	private Activity activity;
	private Employee employee;
	private Date beginTime;

	public BegunActivity(Activity activity, Employee employee, Date date) {
		this.setActivity(activity);
		this.setEmployee(employee);
		this.beginTime = date;
	}
	
	private void setEmployee(Employee employee) {
		this.employee = employee;
	}

	private void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Activity getActivity() {
		return activity;
	}

	public Employee getEmployee() {
		return employee;
	}
	public Date getBegunTime() {
		return this.beginTime;
	}

		
}
