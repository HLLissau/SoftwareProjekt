package timeManagement;
/*
 * Made by : Harald
 */
public class Activity extends ActivityAndProjectParent {
	

	public Activity(String name) {
		super(name);
	}
	public void addEmployee(Employee employee) throws Exception {
		if (employeeList.contains(employee)) {
			throw new Exception("Employee already added to the activity");
		}
		
		employeeList.add(employee);
		employee.setActivity(this);
	}
	public void removeEmployee(Employee e) throws Exception {
		if (!employeeList.contains(e)) {
			throw new Exception("Employee not found in activity");
		}
		employeeList.remove(e);
		
	}
	
}
