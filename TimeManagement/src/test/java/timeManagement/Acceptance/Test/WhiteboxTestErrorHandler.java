package timeManagement.Acceptance.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WhiteboxTestErrorHandler {
	ErrorMessageHolder e = new ErrorMessageHolder();
	
	// returns string when set
	@Test
	void testReturnString() {
		String msg = "Some error message";
		e.setErrorMessage(msg);
		assertEquals(msg, e.getErrorMessage());
	}
	
	// returns null when null is received
	@Test
	void testNullString() {
		String msg = null;
		e.setErrorMessage(msg);
		assertEquals(msg, e.getErrorMessage());
	}
	
	// return empty string when empty string is received
	@Test
	void testEmptyString() {
		String msg = "";
		e.setErrorMessage(msg);
		assertEquals(msg, e.getErrorMessage());
	}
}
