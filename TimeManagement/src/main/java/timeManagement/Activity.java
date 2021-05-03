package timeManagement;

public class Activity extends ActivityAndProjectParent {
	private int budgettedTime;

	public Activity(String name) {
		super(name);
	}

	public void setBudgettedTime(int time) {
		this.budgettedTime = time;
	}
	
	public int getTime() {
		return budgettedTime;
	}

	public void addEmployee(Employee employee) throws Exception {
		if (!employeeList.contains(employee)) {
			employeeList.add(employee);
			employee.setActivity(this);
		} else {
			throw new Exception("Employee already added to the activity");
		}
	}
	
}
