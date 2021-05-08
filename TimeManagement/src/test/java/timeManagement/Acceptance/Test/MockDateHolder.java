package timeManagement.Acceptance.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import timeManagement.DateServer;
import timeManagement.TimeManagement;


public class MockDateHolder {
	
	DateServer dateServer = mock(DateServer.class);
	
	public MockDateHolder(TimeManagement timeManagement) {
		GregorianCalendar calendar = new GregorianCalendar();
		setTime(calendar);
		timeManagement.setDateServer(dateServer);
	}

	public void setTime(Calendar calendar) {
		Calendar c = calendar;
		when(this.dateServer.getTime()).thenReturn(c);
	}
		
	public void advanceTimeByMinutes(int minutes) {
		Calendar currentDate = dateServer.getTime();
		// Important: we need to create a new object,
		// otherwise, the old calendar object gets changed,
		// which suddenly changes the date for objects 
		// using that old calendar object
		Calendar nextDate = new GregorianCalendar();
		nextDate.setTime(currentDate.getTime());
		
		nextDate.add(Calendar.MINUTE, minutes);
		setTime(nextDate);
	}
}
