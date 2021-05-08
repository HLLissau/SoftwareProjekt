package UserInterface;


import java.util.ArrayList;
import java.util.Scanner;

import timeManagement.Activity;
import timeManagement.ActivityAndProjectParent;
import timeManagement.Employee;
import timeManagement.OperationNotAllowedException;
import timeManagement.Project;
import timeManagement.TimeManagement;

public class Interface {
	private static Scanner scanner;
	private static TimeManagement timeManagement;
	private static Employee e;
	private static ArrayList<Activity> al;
	private static Project project;
	private static ArrayList<Project> pl;
	private static Activity activity;
	private static ActivityAndProjectParent workingonapp;

	public static void main(String[] args) {
		timeManagement = new TimeManagement();
		scanner = new Scanner(System.in);
		testsetting();
		mainMenu();
	}
	public static void testsetting() {
		timeManagement.adminLogin("adminadmin");
		Employee e = new Employee("Harald","Lissau","S204436@student.dtu.dk");
		try {
			timeManagement.createEmployee(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 e = new Employee("Anton","Engelbrecht","S200859@student.dtu.dk");
		try {
			timeManagement.createEmployee(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			timeManagement.createProject(new Project("TimeManagement"));
		} catch (OperationNotAllowedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		timeManagement.adminlogout();
	}


	private static void mainMenu() {
		/*
		 * Besked til brugeren
		 */
		clearConsole();
		System.out.println("Velkommen til TimeManagement, venligst log in for at fortsætte:");
		System.out.println("1: Login som administrator");
		System.out.println("2: Login som medarbejder");
		System.out.println("3: Exit");
		System.out.println("Venligst skriv dit valg her: ");	
		
		/*
		 * User input tracking selection
		 */
		int selection =scannerInt(1,3);

		while (true) {
			
			switch (selection) {
			case 1: 
				loginAdmin();
				break;
			case 2: 
				loginEmployee();
				break;
			case 3:
				System.out.println("Tak for i dag.");
				System.exit(selection);
			default:
				System.out.println("Du skal taste et tal mellem 1 og 3.");
				selection = scannerInt(1,3);
				break;
			}
			
		}

	}
	
	public static void adminMenu() {
		clearConsole();
		System.out.println("Du er nu logget på som administrator, her er dine muligheder:");
		System.out.println("1: Opret ny bruger");
		System.out.println("2: Se ledige medarbejdere");
		System.out.println("3: Slet bruger");
		System.out.println("4: Opret nyt projekt");
		System.out.println("5: Se projekter");
		System.out.println("6: Logout");
		while (true) {
			int adminvalg = scannerInt(1,6);
					
			switch(adminvalg) {
			
			case 1: createNewUser();
					break;
			case 2: getAllUsers();
					System.out.println();
					adminMenu();
					break;
			case 3: deleteUser();
					break;
			case 4: createNewProject();		
					break;
			case 5: getAllProjects();
					System.out.println();
					adminMenu();
					break;
			
			case 6: timeManagement.adminlogout();
					mainMenu();
					break;
			}
			
		}
	}
	
	


	private static void getAllProjects() {
		System.out.println("Disse projekter findes i TimeManagement.");
		for( Project p: timeManagement.getAllProjects()) {
			printProject(p);
		}
	}



	private static void createNewProject() {
		clearConsole();
		System.out.println("Opret nyt projekt.");
		System.out.println("Indtast navn");
		String name = scannerString();


		System.out.println("Bekræft det nye projektnavn: ");
		System.out.println("Navn: " + name);
		
		System.out.println();
		System.out.println("Er denne information korrekt?");
		System.out.println("1: Ja");
		System.out.println("2: Nej");
		int i = scannerInt(1,2);
		switch (i) {
		case 1: 

			Project p = new Project(name);

			try {
				timeManagement.createProject(p);
				Project test =timeManagement.getProject(p.getID());
				if(test!=null) {
					System.out.println("Det nye projekt findes som: ");
					printProject(test);
				}
				
			} catch (OperationNotAllowedException e1) {
				System.out.println(e1.getMessage());
			}
			adminMenu();
			break;
		case 2: createNewProject();
		break;

		}
	}



	private static void printProject(Project p) {
		System.out.print("Navn: " +p.getName());
		System.out.print(",   ID: " +p.getID());
		if(p.getProjectManager()!=null) {
			System.out.println(",   Projektleder-navn: " + p.getProjectManager().getFirstName());
			System.out.print(",   Projektleder-ID: " + p.getProjectManager().getID());
		}else {
			System.out.println(",   Projektleder:   projektleder ikke tildelt");
		}
		
		System.out.println();
	}



	public static void employeeMenu() {
		System.out.println("Du er nu logget på som medarbejder, her er dine muligheder:");
		System.out.println("1: Se dine aktiviteter");
		System.out.println("2: Se dine projekter");
		System.out.println("3: Start arbejde på aktivitet");
		System.out.println("4: Se alle projekter");
		System.out.println("5: Tildel projektleder på projekt");
		System.out.println("6: Logout");
		
		
		while (true) {
			int valg = scannerInt(1,6);
					
			switch(valg) {
			
			case 1: 
					System.out.println("Dine aktiviteter: ");
					printActivityList(e.getActivityList());
					System.out.println();
					employeeMenu();
					break;
					
			case 2: 
					System.out.println("Dine Projekter: ");
					printProjectList(e.getProjectList());
					System.out.println();
					employeeMenu();
					break;
			case 3: startWorkOnActivity();
					System.out.println();
					break;
			
			case 4: getAllProjects();
					System.out.println();
					break;
			case 5: setProjectLeaderOnProject();
					employeeMenu();		
					break;
			case 6: mainMenu();
					break;
					
					
			}
		}
		
	}
	
	private static void setProjectLeaderOnProject() {
		project = vælgProjekt(timeManagement.getAllProjects());
		String newLeader= getUser();
		if(newLeader==null) {
			System.out.println("Der er ingen medarbejdere på Projektet endnu");
			System.out.println("Du er blevet sat til projektleder");
			try {
				timeManagement.setProjectManager(project.getID(),e.getID(), e.getID());
			} catch (OperationNotAllowedException e) {
				System.out.println(e.getMessage());
			}
		}else if (project!=null){
			try {
				timeManagement.setProjectManager(project.getID(),newLeader, e.getID());
				printProject(project);
			} catch (OperationNotAllowedException e) {
				System.out.println(e.getMessage());
			}
		}
	}



	private static String getUser() {
		ArrayList<Employee> list = timeManagement.getProject(project.getID()).getEmployeeList();
		if(list.size()==0) {
			System.out.println("Der er medarbejdere at vælge imellem");
			return null;
		}
		
		System.out.println("Vælg en medarbejder");
		for (int i=1; i<=list.size();i++) {
			System.out.print(i+ ": " );
			printEmployee(list.get(i-1));
			System.out.println();
		}
		int valg=scannerInt(1, list.size())-1;
		
		return list.get(valg).getID();
		
	}



	private static Project vælgProjekt(ArrayList<Project> allProjects) {
		
		if(allProjects.size()==0) {
			System.out.println("Der er ingen projekter at vælge imellem endnu");
			return null;
		}
		
		System.out.println("Vælg et projekt");
		for (int i=1; i<=allProjects.size();i++) {
			System.out.print(i+ ": " );
			printActivity(allProjects.get(i-1));
			System.out.println();
		}
		int valg=scannerInt(1, allProjects.size())-1;
		
		return allProjects.get(valg);
		
	}



	private static void startWorkOnActivity() {
		al= e.getActivityList();
		pl= e.getProjectList();
		if(al.size()==0 && pl.size()==0) {
			System.out.println("Du har ingen aktiviter endnu");
			employeeMenu();
		}
		
		System.out.println("Vælg en aktivitet eller projekt at arbejde på");
		for (int i=0; i<al.size();i++) {
			System.out.print((i+1) + ": " );
			printActivity(al.get(i));
			System.out.println();
		}
		for (int i=0; i<pl.size();i++) {
			System.out.print((i+1+al.size()) + ": " );
			printActivity(pl.get(i));
			System.out.println();
		}	
		int max= al.size()+pl.size();
		int aktivitet = scannerInt(1, max);
		if (aktivitet<=al.size()) {
			activity =al.get(aktivitet);
			beginwork(activity);
		}
		
		
	}



	private static void beginwork(ActivityAndProjectParent app) {
		workingonapp= app;
		try {
			timeManagement.beginWorkOnActivity(e.getID(), app.getID());
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
	}



	private static void printProjectList(ArrayList<Project> projectList) {
		for(Project app : projectList) {
			printProject(app);
		}
	}
	private static void printActivityList(ArrayList<Activity> activityList) {
		for(ActivityAndProjectParent app : activityList) {
			printActivity(app);
		}
	}
	private static void printActivity(ActivityAndProjectParent app) {
		System.out.print("Navn: "+ app.getName());
		System.out.print(",   ID: " + app.getID() );
		
	}
	private static void deleteUser() {
		System.out.println("ADVARSEL:");
		System.out.println("Du er ved at slette en bruger fra systemet");
		System.out.println("Skrive 0 for at returnere");
		System.out.println("Indtast brugerID");
		String id = scannerString();
		if (id.equals("0")) {
			adminMenu();
		}
		Employee e =timeManagement.getEmployee(id);
		if(e==null) {
			System.out.println(e + "id : " + id);
			System.out.println("Brugeren findes ikke i TimeManagement");
			deleteUser();
		}
		
		System.out.println("Vil du slette denne bruger?");
		System.out.println("1: ja");
		System.out.println("2: nej");
		printEmployee(e);
		int i = scannerInt(1,2);
		switch (i) {
		case 1: 

			try {
				timeManagement.removeEmployeeFromTimeManagement(id);
				Employee test =timeManagement.getEmployee(e.getID());
				if(test==null) {
					System.out.println("Brugeren er slettet");
					
				}
				
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			adminMenu();
			break;
		case 2: 	deleteUser();
		break;

		}
		
		
		
	}



	private static void getAllUsers() {
		System.out.println("List of all employees.");
		for( Employee e: timeManagement.getAllEmployees()) {
			printEmployee(e);
		}
		
	}



	public static void createNewUser() {
		clearConsole();
		System.out.println("Opret ny bruger.");
		System.out.println("Indtast fornavn ");
		String firstName = scannerString();

		System.out.println("Indtast efternavn: ");
		String lastName = scannerString();
		System.out.println("indtast email");
		String email = scannerString();

		System.out.println("Bekræft den nye bruger: ");
		System.out.println("Fornavn:" + firstName);
		System.out.println("Efternavn: " + lastName);
		System.out.println("Email: " + email);

		System.out.println();
		System.out.println("Er denne information korrekt?");
		System.out.println("1: ja");
		System.out.println("2: nej");
		int i = scannerInt(1,2);
		switch (i) {
		case 1: 

			Employee e = new Employee(firstName,lastName,email);

			try {
				timeManagement.createEmployee(e);
				Employee test =timeManagement.getEmployee(e.getID());
				if(test!=null) {
					System.out.println("Den nye bruger findes i TimeManagement som:");
					printEmployee(test);
				}
				
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			adminMenu();
			break;
		case 2: System.out.flush();
		createNewUser();
		break;

		}
	}
	
	private static void printEmployee(Employee e) {
		System.out.print("First name: " +e.getFirstName());
		System.out.print(",   Last name: " +e.getLastName());
		System.out.print(",   ID: " +e.getID());
		System.out.println();
		
	}



	public static void loginAdmin(){
		System.out.println("Du er nu i admin-login. \nIndtast din adgangskode. Skriv 2 for at gå tilbage.");
		System.out.println("Adgangskode: ");
		String brugerID = scannerString();
		while (!brugerID.equals("2")) {

			timeManagement.adminLogin(brugerID);

			if(timeManagement.adminLoggedIn()) {
				adminMenu();
			}else {
				System.out.println();
				System.out.println("Forkert kode, prøv igen");
			}
			brugerID = scannerString();
		}
		mainMenu();
	}
	public static void loginEmployee(){
		System.out.println("Du er nu i medarbejder-login. \nIndtast dit bruger-ID. Skriv 2 for at gå tilbage.");
		System.out.println("Bruger-ID: ");
		String brugerID = scannerString();
		while (!brugerID.equals("2")) {

			e = timeManagement.getEmployee(brugerID);

			if(e!=null) {
				employeeMenu();
			} 
			System.out.println("Forkert brugerID, prøv igen");
			brugerID = scannerString();
		}
		mainMenu();
		
	}
	/*
	 * Check input is a number within the set limit
	 */
	public static int scannerInt(int min, int max){	
		int i = 0;
		while (true) {
			while (!scanner.hasNextInt()){
				scanner.next();
			}	
			i = scanner.nextInt();
			if (( min <= i && i <= max )) {
				break;
			}
		}	
		assert i>min && i<max;
		return  i;
	}
	public static String scannerString() {
		String textOutput = ("");
		if (scanner.hasNext()){
			textOutput = scanner.next();
		}
		
		return textOutput;
	}
	
	public final static void clearConsole()  {
		/*
		 * clearscreen 
		 */
		try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        // System.out.println(e.getMessage());
	    }
		    
		
	}
	
}
