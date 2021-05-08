package timeManagement;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

public class Interface {
	private static Scanner scanner;
	private static TimeManagement timeManagement;

	public static void main(String[] args) {
		timeManagement = new TimeManagement();
		scanner = new Scanner(System.in);
		mainMenu();
	}



	private static void mainMenu() {
		System.out.println("Velkommen til TimeManagement, venligst log in for at fortsætte:");

		System.out.println("1: Login som administrator");
		System.out.println("2: Login som medarbejder");
		System.out.println("3: Exit");

		System.out.println("Venligst skriv dit valg her: ");	
		int selection = 0;

		while (selection != 3) {
			selection = scanner.nextInt();
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
				System.out.println("Du skal taste et tal mellem 1 og 4.");
				break;
			}
		}

	}
	
	public static void adminMenu() {
		System.out.println("Du er nu logget på som administrator, her er dine muligheder:");
		
	}
	
	public static void employeeMenu() {
		System.out.println("Du er nu logget på som medarbejder, her er dine muligheder:");
	}
	public static void loginAdmin(){
		System.out.print("Adgangskode: ");
		String brugerID = scanner.nextLine();
		while (true) {

			timeManagement.adminLogin(brugerID);

			if(timeManagement.adminLoggedIn()) {
				adminMenu();
			}else {
				System.out.println("Forkert kode, prøv igen");
			}
			brugerID = scanner.nextLine();
		}
	}
	public static void loginEmployee(){
		System.out.print("BrugerID: ");
		String brugerID = scanner.nextLine();
		while (true) {

			timeManagement.adminLogin(brugerID);

			if(timeManagement.adminLoggedIn()) {
				employeeMenu();
			}else {
				System.out.println("Forkert brugerID, prøv igen");
			}
			brugerID = scanner.nextLine();
		}
	}
}
