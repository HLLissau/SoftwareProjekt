package timeManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RegisterTime {
	private ArrayList<BegunActivity> begunActivities;
	
	public RegisterTime(DateServer dateServer) {
		this.begunActivities = new ArrayList<BegunActivity>();
	}
	
	
	public void setBeginTime(Activity activity, Employee employee, Date date) throws OperationNotAllowedException {
		if(getBegunActivity(activity,employee) != null) {
			throw new OperationNotAllowedException("Employee already working on the activity");
		}
		begunActivities.add(new BegunActivity (activity,employee,date));
	}
	
	public  Date setFinishedTime(Activity activity, Employee employee, Date date) throws Exception {
		BegunActivity begunActivity = getBegunActivity(activity,employee);
		if(begunActivity.equals(null)) {
			throw new OperationNotAllowedException("Employee not working on the activity");
		}
	
		Long beginTime = begunActivity.endActivity().getTime();
		Long endTime = date.getTime();
		Long timeDiff =  endTime-beginTime;
		int diffInMinutes =  (int) (timeDiff / (1000 * 60));
		activity.registerTimeSpent(diffInMinutes);
		begunActivities.remove(begunActivity);
		activity.removeEmployee(employee);
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
