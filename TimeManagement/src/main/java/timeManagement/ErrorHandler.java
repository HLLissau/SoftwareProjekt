package timeManagement;

public class ErrorHandler extends Exception {
	private String errorMessage;
	/*
	 * 
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
	public void setErrorMessage(String message) {
		this.errorMessage=message;
	}

}
