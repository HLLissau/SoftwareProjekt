package timeManagement;


public class Employee {

	private String iD;
	private String firstName;
	private String lastName;
	private String email;

	public Employee(String firstName, String lastName, String email) {
		// firstname, lastname must not be less than 2 chars long
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
	}
	public void setID(String iD) {
		this.iD=iD;
	}
	
	public String getID() {
		return this.iD;
	}
	
	protected String getFirstName() {
		return firstName;
	}
	
	protected String getLastName() {
		return lastName;
	}
}
