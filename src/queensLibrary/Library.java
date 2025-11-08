package queensLibrary;

import java.util.ArrayList;

/**
 * Represents a library which holds an array list of books with methods available to interact with the library.
 */
public class Library {
	private ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
	
	/**
	 * Public Constructor that creates a library with no books
	 */
	public Library() {
		
	}
	
	/**
	 * Public Constructor that creates a library with one book.
	 * @param book The one book that is put into the library.
	 */
	public Library(LibraryBook book) {
		books.add(book);
	}
	
	/**
	 * Public Constructor that creates a library with multiple books.
	 * @param books An array list of LibraryBooks
	 */
	public Library(ArrayList<LibraryBook> books) {
		this.books = books;
	}
	
	/**
	 * Method to borrow a book that uses the checkout function
	 * @param id book to be borrowed
	 * @return returns false if the book could not be borrowed
	 */
	public boolean borrowBook(int id) {
		LibraryBook book = search(id);
		if (book != null) {
			if (book.checkout()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to return a book that uses the checkIn function
	 * @param id book to be returns
	 * @return returns false if the book could not be returned
	 */
	public boolean returnBook(int id) {
		LibraryBook book = search(id);
		if (book != null) {
			if (book.checkIn()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Loops through all books in ArrayList and adds them to an array
	 * @return An array of all LibraryBook books
	 */
	public LibraryBook[] list() {
		int index = 0;
		LibraryBook[] arrBooks = new LibraryBook[books.size()];
		for (LibraryBook book : books) {
			arrBooks[index] = book;
			index++;
		}
		return arrBooks;
	}
	
	/**
	 * Loops through all books in ArrayList and compares the status of each book to the @param. If this returns true the methods adds them to an array.
	 * @param status Used to compare each bookStatus with.
	 * @return An array of all books with the valid status.
	 */
	public LibraryBook[] list(BookStatus status) {
		ArrayList<LibraryBook> refinedBooks = new ArrayList<LibraryBook>();
		for (LibraryBook book : books) {
			if (book.getStatus() == status) {
				refinedBooks.add(book);
			}
		}
		int index = 0;
		LibraryBook[] arrBooks = new LibraryBook[refinedBooks.size()];
		for (LibraryBook book : refinedBooks) {
			arrBooks[index] = book;
			index++;
		}
		return arrBooks;
	}
	
	/**
	 * Removes book of certain id
	 * @param id
	 * @return true if removing book was successful
	 */
	public boolean removeBook(int id) {
		LibraryBook bookToRemove = search(id);
		if (bookToRemove != null) {
			books.remove(bookToRemove);
			return true;
		}
		return false;
	}
	
	/**
	 * Uses a simple bubble sort to sort through an array of all books and sorts them by loan count.
	 * @return An array of LibraryBook sorted by loan count.
	 */
	public LibraryBook[] mostPopular() {
		LibraryBook[] sortedBooks = list();
		boolean swapsMade = true;
		while (swapsMade == true) {
			swapsMade = false;
			for (int i = 0; i < sortedBooks.length-1; i++) {
				if (sortedBooks[i].getLoanCount() < sortedBooks[i+1].getLoanCount()) {
					LibraryBook temp = sortedBooks[i+1];
					sortedBooks[i+1] = sortedBooks[i];
					sortedBooks[i] = temp;
					swapsMade = true;
				}
			}
		}
		return sortedBooks;
	}

	/**
	 * Uses a simple linear search algorithm and return the LibraryBook of the correct index.
	 * @param id The index of the book we are searching for.
	 * @return The LibraryBook at the index.
	 */
	public LibraryBook search(int id) {
		for (LibraryBook book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	/**
	 * Adds a LibraryBook to the library and performs some simple validation.
	 * @param libraryBook The LibraryBoook that is to be added to the library.
	 * @return True if the book is successfully added.
	 */
	public boolean add(LibraryBook libraryBook) {
		if (libraryBook.getPrice() > 0.00) {
			books.add(libraryBook);
			return true;
		}
		return false;
			
	}
}
