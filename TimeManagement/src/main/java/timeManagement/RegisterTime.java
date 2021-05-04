package timeManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RegisterTime {
	private ArrayList<BegunActivity> begunActivity;
	private DateServer dateServer;

	public RegisterTime(DateServer dateServer) {
		this.dateServer = dateServer;
		this.begunActivity = new ArrayList<BegunActivity>();
	}
	
	
	public void setBeginTime(Activity a, Employee e) throws OperationNotAllowedException {
		if(getBegunActivity(a,e)!=null) {
			throw new OperationNotAllowedException("Employee already working on the activity");
		}
		
		begunActivity.add(new BegunActivity(a,e,dateServer.getTime().getTime()));
	}
	public void setFinishedTime(Activity a, Employee e) throws Exception {
		
		BegunActivity ba = getBegunActivity(a,e);
		if(ba.equals(null)) {
			
			throw new OperationNotAllowedException("Employee not working on the activity");
		}
		
		Long begintime =ba.endActivity().getTime();
		Long endTime = dateServer.getTime().getTime().getTime();
		Long differenceInTIme =  endTime-begintime;
		int diffInMinutes =  (int) (differenceInTIme / (1000 * 60));
		System.out.println("begin" + begintime + ", end:" +endTime);
		a.registerTimeSpent(diffInMinutes);
		begunActivity.remove(ba);
		a.removeEmployee(e);
	}
	
	
	private BegunActivity getBegunActivity(Activity a, Employee e) {
		return begunActivity.stream().filter(ba -> (ba.getA().equals(a) && ba.getE().equals(e))).findAny().orElse(null);
	}

	public Date getBeginTimeOfActivityByEmployee(Activity a, Employee e) throws OperationNotAllowedException {
		BegunActivity ba = getBegunActivity(a,e);
		
		if(ba==null) {
			throw new OperationNotAllowedException("Employee not working on the activity");
		}
		return ba.getBegunTime();
	}
	

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}
	
	

	
}
