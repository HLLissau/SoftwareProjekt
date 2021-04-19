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
	
}
