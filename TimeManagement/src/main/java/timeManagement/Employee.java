package timeManagement;

public class Employee {

	private int iD;
	private String name;
	private String email;

	public Employee(String name, String email) {
		this.name=name;
		this.email=email;
		
	}
	public void setID(int iD) {
		this.iD=iD;
	}
	
	public int getID() {
		return this.iD;
	}

}
