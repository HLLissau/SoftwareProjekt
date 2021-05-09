package timeManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RegisterTime {
	private ArrayList<BegunActivity> begunActivities;
	private int diffInMinutes = 0;
	
	public RegisterTime() {
		this.begunActivities = new ArrayList<BegunActivity>();
	}
	
	public int getAmountOfBegunActivities() {
		return begunActivities.size();
	}
	
	/*
	 * Following 2 getter/setter methods are for TESTING ONLY.
	 * NB: The diffInMinutes variable in setFinishedTime() CANNOT be altered
	 * by the setter function. It is used exclusively for handing the value of diffInMinutes
	 * from the setFinishedTime function to the testing class.
	 */
	
	private void setDiffInMinutes(int diffInMinutes) {
		this.diffInMinutes = diffInMinutes;
	}
	
	public int getDiffInMinutes() {
		return this.diffInMinutes;
	}
	
	
	
	public void setBeginTime(Activity activity, Employee employee, Date date) throws OperationNotAllowedException {
		if(getBegunActivity(activity,employee) != null) {
			throw new OperationNotAllowedException("Employee already working on the activity");
		}
		begunActivities.add(new BegunActivity (activity,employee,date));
	}
	
	public  Date setFinishedTime(Activity activity, Employee employee, Date date) throws Exception {
		BegunActivity begunActivity = getBegunActivity(activity,employee);
		if(begunActivity ==null) {
			throw new OperationNotAllowedException("Employee not working on the activity");
		}
	
		Long beginTime = begunActivity.getBeginTime().getTime();
		Long endTime = date.getTime();
		Long timeDiff =  endTime-beginTime;
		int diffInMinutes =  (int) (timeDiff / (1000 * 60));
		setDiffInMinutes(diffInMinutes); // for testing only
		activity.registerTimeSpent(diffInMinutes);
		begunActivities.remove(begunActivity);
		return date;
	}
	
	
	private BegunActivity getBegunActivity(Activity a, Employee e) {
		return begunActivities.stream().filter(ba -> (ba.getActivity().equals(a) && ba.getEmployee().equals(e))).findAny().orElse(null);
	}
	
	public Date getBeginTimeOfActivityByEmployee(Activity activity, Employee employee) throws OperationNotAllowedException {
		BegunActivity begunActivity = getBegunActivity(activity,employee);
		
		if(begunActivity == null) {
			throw new OperationNotAllowedException("Employee not working on the activity");
		}
		return begunActivity.getBegunTime();
	}
	
}
