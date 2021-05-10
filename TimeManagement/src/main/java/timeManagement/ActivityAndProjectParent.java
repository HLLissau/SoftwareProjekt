package timeManagement;

import java.util.ArrayList;

public class ActivityAndProjectParent {
	protected String name;
	protected String description;
	protected ArrayList<Employee> employeeList;
	protected int budgettedTime=0;
	protected int registerTimeSpent=0;
	private int id;
	protected int time;
	public ActivityAndProjectParent(String name){
		this.name = name;
		this.employeeList=new ArrayList<Employee>();
	}
	public void setBudgettedTime(int time) {
		this.budgettedTime = time;
		this.registerTimeSpent=0;
	}
	
	public int getTimeRemaining() {
		return budgettedTime-registerTimeSpent;
	}
	public int getID() {
		return this.id;
	}
	public void registerTimeSpent(int diffInMinutes) {
		this.registerTimeSpent += diffInMinutes;
	}

	protected void setID(int id) {
		this.id=id;
	}

	public int getTimeSpent() {
		return registerTimeSpent;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	protected void setExpectedTime(int time) {
		this.time=time;
	}
	public ArrayList<Employee> listEmployees() {
		return this.employeeList;
		
	}
	public ArrayList<Employee> getEmployeeList() {
		return this.employeeList;
	}
	public int getTime() {
		
		return this.time;
	}
}
