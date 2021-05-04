package timeManagement;

import java.util.Date;
import java.util.GregorianCalendar;

public class BegunActivity {
	private Activity a;
	private Employee e;
	private Date beginTime;

	public BegunActivity(Activity a, Employee e, Date date) {
		this.setA(a);
		this.setE(e);
		
		this.beginTime = date;
	}
	
	private void setE(Employee e2) {
		e=e2;
	}

	private void setA(Activity a2) {
		a=a2;
	}

	public Date endActivity() {
		return beginTime;
	}

	public Activity getA() {
		return a;
	}

	public Employee getE() {
		return e;
	}
	public Date getBegunTime() {
		return this.beginTime;
	}

		
}
