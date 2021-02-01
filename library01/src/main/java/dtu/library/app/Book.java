package dtu.library.app;

/**
 * This class represents a book with title, author, and signature, where signature
 * is a unique key used by the librarian to identify the book. Very often it is 
 * composed of the first letters of the authors plus the year the book was published.
 * @author Hubert
 *
 */
public class Book {
	private String title;
	private String author;
	private String signature;
	public Book(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}
	public String getSignature() {
		// TODO Auto-generated method stub
		return this.signature;
	}
}
