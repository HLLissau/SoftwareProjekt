package timeManagement;

import java.util.Calendar;
import java.util.GregorianCalendar;

// this is NOT made by the group but borrowed from LibraryApp



// The DateServer has not 100% code coverage, as it basically replaced immediately by the
// mock date server for testing.
public class DateServer {

	/**
	 * Return the current date without the current time.
	 * @return the current date without the current time
	 */
	public Calendar getTime() {
		Calendar calendar = new GregorianCalendar();
		Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		return c;
	}

	public int getYear() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(calendar.YEAR);
	}
}
