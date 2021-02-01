package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryApp {
	private boolean adminLoggedIn = false;
	private ArrayList<Book> books= new ArrayList<>();
	
	public void addBook(Book book) throws OperationNotAllowedException {
		books.add(book);
		}

	public boolean containsBookWithSignature(String signature) {
		boolean bookPresent = false;
		for (Book book : books) {
			if (book.getSignature().equals(signature)){
				bookPresent=true;
			}
				
		}
		return bookPresent;
	}

	public boolean adminLoggedIn() {
		
		return adminLoggedIn;
	}

	public boolean adminLogin(String password) {
		adminLoggedIn = password.equals("adminadmin");
		return adminLoggedIn;
	}
	public void adminLogout() {
		adminLoggedIn=false;
	}
}
