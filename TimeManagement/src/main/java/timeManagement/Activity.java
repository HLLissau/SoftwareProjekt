package timeManagement;

public class Activity extends ActivityAndProjectParent {
	private int budgettedTime;
	private int registerTimeSpent;

	public Activity(String name) {
		super(name);
	}

	public void setBudgettedTime(int time) {
		this.budgettedTime = time;
		this.registerTimeSpent=0;
	}
	
	public int getTimeRemaining() {
		return budgettedTime-registerTimeSpent;
	}

	public void addEmployee(Employee employee) throws Exception {
		if (!employeeList.contains(employee)) {
			employeeList.add(employee);
			employee.setActivity(this);
		} else {
			throw new Exception("Employee already added to the activity");
		}
	}

	public void registerTimeSpent(int diffInMinutes) {
		this.registerTimeSpent += diffInMinutes;
	}
	public int getTimeSpent() {
		return registerTimeSpent;
	}

	public void removeEmployee(Employee e) throws Exception {
		if (!employeeList.contains(e)) {
			throw new Exception("Employee not found in activity");
		}
		employeeList.remove(e);
		e.removeActivity(this);
	}
	
}
