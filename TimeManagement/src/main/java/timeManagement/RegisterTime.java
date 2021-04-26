package timeManagement;

import java.util.GregorianCalendar;

public class RegisterTime {
	private GregorianCalendar calendar;

	public RegisterTime() {
		this.calendar = new GregorianCalendar();
	}
	
	public int getYear() {
		return this.calendar.get(calendar.YEAR);
	}
	
}
