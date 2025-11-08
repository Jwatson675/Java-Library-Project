package queensLibrary;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import console.Console;

/**
 * Application Class for QUBLibrary
 * This class initialises the library and scanner Classes and starts the main program. 
 */
public class QUBLibrary {
	
	public Library library = new Library();
	public Console consoleInput = new Console(true);
	public Console consoleImage = new Console(true);
	public Console consoleOutput = new Console(true);
	public Console consoleTitle = new Console(true);
	public Console loading = new Console(true);
	
	/**
	 * Returns true if all characters in a string are digits.
	 * @param digits the String of characters
	 * @return true if all characters are digits false if not
	 */
	private boolean allDigits(String digits) {
		boolean allDigits = true;
		for (char c : digits.toCharArray()) {
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				break;
			default:
				allDigits = false;
			}
		}
		return allDigits;
	}
	
	/**
	 * Performs a simple linear search
	 * @param arr the array that is being searched
	 * @param value the value that is being searched for
	 * @return return true if the array contains the value
	 */
	private boolean arrayContains(int[] arr, int value) {
		for (int x : arr) {
			if (x == value)
				return true;
		}
		return false;
	}
	
	/**
	 * Clears image & output screen once return is entered
	 * @param gap leaves a gap before "Press to enter"
	 */
	private void clearWindows(boolean gap) {
		if (gap)
			consoleInput.println();
		consoleInput.print("Press enter to return");
		consoleInput.readLn();
		consoleImage.clear();
		consoleOutput.clear();
	}
	
	/**
	 * Updates the title bar Image depending on the title passed in
	 * @param title the image name
	 */
	private void updateTitleBar(String title) {
		consoleTitle.clear();
		ImageIcon img = new ImageIcon(String.format("Images/%s",title));
		consoleTitle.print(img);
	}
	
	/**
	 * Lists each book details in the appropriate display
	 * @param book of whose details is displayed
	 */
	private void listBookDetails(LibraryBook book) {
		ImageIcon img = new ImageIcon(String.format("Images/%s",book.getImage()));
		consoleImage.println(String.format("BookID:%d, Book Title:%s", book.getId(),book.getTitle()));
		consoleImage.println(img);
		consoleImage.println();
		
		for (String data : book.toString().split(",")) {
			// Prints attribute in bold text
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print(data.trim().split(":")[0]);
			consoleOutput.print(":");
			// Prints value in plain text
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(data.trim().split(":")[1]);
		}
		consoleOutput.println();
	}
	
	/**
	 * Lists all books from the library. Uses a function of library to return an array of the books,
	 * and iterates through that array.
	 */
	public void listAllBooks() {
		updateTitleBar("listAllBooks.jpg");
		consoleImage.setFont(new Font("Courier", Font.BOLD,15));
		for (LibraryBook book : library.list()) {
			listBookDetails(book);
		}
		clearWindows(false);
	}
	
	/**
	 * Lists all books from the library that have a certain status.
	 */
	public void listBooksByStatus() {
		updateTitleBar("listBooksByStatus.jpg");
		// Creates a menu for the user to choose from which provides validation
		String[] statusOptions = new String[] {"AVAILABLE", "ON_LOAN","WITHDRAWN"};
		Menu statusMenu = new Menu(statusOptions,"Book Status",consoleInput);
		consoleInput.println(statusMenu);
		int statusChoice = statusMenu.getInput();
		BookStatus status = null;
		switch (statusChoice) {
		case 1:
			status = BookStatus.AVAILABLE;
			break;
		case 2:
			status = BookStatus.ON_LOAN;
			break;
		case 3:
			status = BookStatus.WITHDRAWN;
			break;
		}
		
		// Lists all books with the chosen status
		consoleImage.setFont(new Font("Courier", Font.BOLD,15));
		// Checks that there are book to display with the chosen status
		if (library.list(status).length > 0) {
			for (LibraryBook book : library.list(status)) {
				listBookDetails(book);
			}
		}
		else {
			consoleOutput.println("There are no books available to display");
		}
		
		clearWindows(false);
	}
	
	/**
	 * Lists all books from the library that have a certain status.
	 * @param status
	 */
	public void listBooksByStatus(BookStatus status) {
		consoleImage.setFont(new Font("Courier", Font.BOLD,15));
		for (LibraryBook book : library.list(status)) {
			listBookDetails(book);
		}
	}
	
	/**
	 * Adds a book to the library.
	 * Asks user for each input which are validated and gives an error message if necessary.
	 */
	public void addABook() {
		updateTitleBar("addABook.jpg");
		boolean valid = false;
		while (!valid) {
			// Validation for title
			String title = "";
			while (title.length() < 5 || title.length() >40) {
				consoleInput.print("Please enter book title: ");
				title = consoleInput.readLn().trim();
				if (title.length() < 5 || title.length() >40)
					consoleInput.println("Invalid Input - Title must be between 5 to 40 characters long");
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Title: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("\"%s\"",title));
			
			// Validation for author
			String author = "";
			while (author.length() < 5 || author.length() >40) {
				consoleInput.print("Please enter book author: ");
				author = consoleInput.readLn().trim();
				if (author.length() < 5 || author.length() >40)
					consoleInput.println("Invalid Input - Author must be between 5 to 40 characters long");
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Author: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("\"%s\"",author));
			
			// Validation for isbn
			String isbn = "";
			while (isbn.length() != 10 || !allDigits(isbn)) {
				consoleInput.print("Please enter book ISBN: ");
				isbn = consoleInput.readLn().trim();
				if (isbn.isEmpty()) {
					consoleInput.println("Invalid Input - ISBN cannot be empty");
				}
				else if (isbn.length() != 10 || !allDigits(isbn))
					consoleInput.println("Invalid Input - ISBN must be 10 numbers");
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book ISBN: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(isbn);
			
			// Validation for BookType
			BookType type = null;
			while (type == null) {
				consoleInput.print("Please enter book type (FICTION, NON FICTION, REFERENCE): ");
				String bookTypeInput =consoleInput.readLn().trim().toUpperCase();
				if (bookTypeInput.isEmpty()) {
					consoleInput.println("Invalid Input - BookType cannot be empty");
				} else {
					try {
						// type of book, uses value of and replaces spaces with an underscore to work with the enum
						type = BookType.valueOf(bookTypeInput.replaceFirst(" ", "_"));
					}
					catch (Exception e) {
						consoleInput.println("Invalid Input - BookType must be a valid book type");
					}
				}
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Type: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("\"%s\"",type.toString()));;
			
			// Validation for edition
			int edition = -1;
			consoleInput.print("Please enter the edition number: ");
			while (edition < 1) {
				try {
					String userInput = consoleInput.readLn().trim();
					if (userInput.isEmpty()) {
						throw new Exception("Invalid Input - Edition cannot be empty");
					}
					try {
						edition = Integer.parseInt(userInput);
					} catch (Exception f) {
						throw new Exception("Invalid Input - Edition must be an integer");
					}
					if(edition < 1)
						throw new Exception("Invalid Input - Edition must be greater than 0");
				}
				catch (Exception e){
					consoleInput.println(e.getMessage());
					consoleInput.print("Please enter the edition number: ");
				}
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Edition: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(edition);
			
			// Validation for summary
			String summary = "";
			while (summary.length() < 20 || summary.length() >150) {
				consoleInput.print("Please enter book summary: ");
				summary = consoleInput.readLn().trim();
				if (summary.length() < 20 || summary.length() >150)
					consoleInput.println("Invalid Input - Summary must be between 20 to 150 characters long");
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Summary: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("\"%s\"",summary));
			
			// Validation for price
			double price = -1;
			consoleInput.print("Please enter book price: ");
			while (price <= 0) {
				try {
					String userInput = consoleInput.readLn().trim();
					if (userInput.isEmpty())
						throw new Exception("Invalid Input - Price cannot be empty");
					try {
						price = Double.parseDouble(userInput);
					} catch (Exception f) {
						throw new Exception("Invalid Input - Price must be an a real number");
					}
					if(price <= 0)
						throw new Exception("Invalid Input - Price must be greather than £0.00");
				}
				catch (Exception e){
					consoleInput.println(e.getMessage());
					consoleInput.print("Please enter book price: ");
				}
			}
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Price: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("£%.2f", price));
			
			// Validation for coverImage
			String coverImage = "";
			String answer = null;
			boolean coverImageChoice = false;
			while (!coverImageChoice) {
				try {
					consoleInput.print("Would you like to add a cover image (Y/N): ");
					answer = consoleInput.readLn().toUpperCase();
					if (answer.equals("N") || answer.equals("Y")) {
						coverImageChoice = true;				
					}
					else
						throw new Exception("Invalid Input - You must enter a 'Y' or 'N'");
				}
				catch (Exception e) {
					consoleInput.println(e.getMessage());
				}
									
			}
			if (answer.equals("N"))
				coverImage = "default.jpg";
			else {
				boolean coverImageValid = false;
				while (!coverImageValid) {
					try {
						consoleInput.print("Enter cover image name: ");
						coverImage = consoleInput.readLn();
						// Checks if the file exists in the Images folder
						if (new File("Images/"+ coverImage).isFile())
							coverImageValid = true;
						else
							throw new Exception("Invalid Input - Cover Image must be a valid file in Images (If no file exists enter \"default.jpg\")");
						}
					 catch (Exception e) {
						consoleInput.println(e.getMessage());
					 }
				}
			}
			ImageIcon img = new ImageIcon(String.format("Images/%s",coverImage));
			consoleImage.println(img);
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Book Image File Path: ");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(String.format("\"%s\"",coverImage));

			
			LibraryBook newBookToAdd = new LibraryBook(title, author, isbn, type, edition, summary, price, coverImage);
			if (library.add(newBookToAdd))
				valid = true;
			
			clearWindows(true);		
		}
	}
	
	/**
	 * Removes a book from the library.
	 * Asks the user for the index of the book they would like to remove and makes sure it's available before it is withdrawn.
	 */
	public void removeBook() {
		updateTitleBar("removeABook.jpg");
		LibraryBook[] libraryArray = library.list(BookStatus.AVAILABLE);
		// Checks that there are books to remove
		if (libraryArray.length > 0) {
			consoleOutput.println("Choose a book to remove");
			listBooksByStatus(BookStatus.AVAILABLE);
			
			// Gets an array of the id's of each book that is available
			int[] indexes = new int[libraryArray.length];
			for (int i = 0; i < indexes.length; i++) {
				indexes[i] = libraryArray[i].getId();
			}
			
			boolean indexValid = false;
			int idToRemove = -1;
			while(!indexValid) {
				try {
					consoleInput.print("Enter a BookID to remove (-1 to exit): ");
					String userInput = consoleInput.readLn().trim();
					
					//Checks if input is empty
					if (userInput.isEmpty())
						throw new Exception ("Invalid Input - BookID cannot be empty");
					//Checks if input is an integer
					try {
						idToRemove = Integer.parseInt(userInput);
						if (idToRemove == -1) {
							clearWindows(true);
							return;
						}
					} catch (Exception f) {
						throw new Exception ("Invalid Input - BookID must be an integer");
					}
					if (!arrayContains(indexes, idToRemove)) {
						throw new Exception ("Invalid Input - BookID must be from an Available Book");
					}
					indexValid = true;
				} catch (Exception e) {
					consoleInput.println(e.getMessage());
				}
			}
			// Once the data has been validated the BookStatus can be set to Withdrawn
			library.search(idToRemove).setStatus(BookStatus.WITHDRAWN);
			consoleOutput.clear();
			consoleOutput.print(String.format("The Book (ID: %d) is now removed\n", idToRemove));
			clearWindows(true);
		}
		else {
			consoleOutput.println("There are no books available to remove");
			clearWindows(false);
		}
	}
	
	/**
	 * Borrows a book from the library.
	 * Asks the user for an id of a book to borrow and checks that it is available to borrow before confirming.
	 */
	public void borrowBook() {
		updateTitleBar("borrowABook.jpg");
		if (library.list(BookStatus.AVAILABLE).length > 0) {
			consoleOutput.println("Choose a book to withdraw");
			listBooksByStatus(BookStatus.AVAILABLE);
			
			boolean valid = false;
			int id = -1;
			while(!valid) {
				try {
					consoleInput.print("Please enter a book id to borrow (-1 to exit): ");
					String userInput = consoleInput.readLn().trim();
					//Checks if input is empty
					if (userInput.isEmpty())
						throw new Exception ("Invalid Input - Book ID cannot be empty");
					//Checks if input is an integer
					try {
						id = Integer.parseInt(userInput);
						if (id == -1) {
							clearWindows(true);
							return;
						}
					}
					catch (Exception f){
						throw new Exception("Invalid Input - Book ID must be an integer");
					}
					//Attempts to borrow book and if it cannot be borrowed valid will stay false
					if (!library.borrowBook(id)) {
						throw new Exception("Invalid Input - Enter a valid Book ID that is Available");
					}
					valid = true;
					
				} catch (Exception e) {
					consoleInput.println(e.getMessage());
				}
			}
			consoleOutput.clear();
			consoleOutput.print(String.format("The Book (ID: %d) is now borrowed\n", id));
			clearWindows(true);
		}
		else {
			consoleOutput.println("There are no books available to borrow");
			clearWindows(false);
		}
	}	
	
	/**
	 * Returns a book from the library.
	 * Asks the user for an id of a book to return and checks that it is available to return before confirming.
	 */
	public void returnBook() {
		updateTitleBar("returnABook.jpg");
		if (library.list(BookStatus.ON_LOAN).length > 0) {
			consoleOutput.println("Choose a book to return");
			listBooksByStatus(BookStatus.ON_LOAN);
			
			int id = -1;
			boolean valid = false;
			while(!valid) {
				try {
					consoleInput.print("Please enter a book id to return (-1 to exit): ");
					String userInput = consoleInput.readLn().trim();
					//Checks if input is empty
					if (userInput.isEmpty())
						throw new Exception ("Invalid Input - Book ID cannot be empty");
					//Checks if input is an integer
					try {
						id = Integer.parseInt(userInput);
						if (id == -1) {
							clearWindows(true);
							return;
						}
					}
					catch (Exception f){
						throw new Exception("Invalid Input - Book ID must be an integer");
					}
					//Attempts to return book and if it cannot be returned valid will stay false
					if (!library.returnBook(id)) {
						throw new Exception("Invalid Input - Enter a valid Book ID that is Available");
					}

					valid = true;
				} catch (Exception e) {
					consoleInput.println(e.getMessage());
				}
			}
			consoleOutput.clear();
			consoleOutput.print(String.format("The Book (ID: %d) is now returned\n", id));
			clearWindows(true);
		}
		else {
			consoleOutput.println("There are no books on loan to return");
			clearWindows(false);
		}
	}
	
	/**
	 * Displays a ranked list of the books by order of loan count.
	 */
	public void displayRankedList() {
		updateTitleBar("rankedList.jpg");
		LibraryBook[] ranked = library.mostPopular();
		for (LibraryBook book : ranked) {
			ImageIcon img = new ImageIcon(String.format("Images/%s",book.getImage()));
			consoleImage.println(String.format("BookID:%d, Book Title:%s", book.getId(),book.getTitle()));
			consoleImage.println(img);
			consoleImage.println();
			
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Title:\"");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(book.getTitle()+"\"");
			
			
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Author:\"");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(book.getAuthor()+"\"");
			
			consoleOutput.setFont(new Font("Courier", Font.BOLD,15));
			consoleOutput.print("Loan Count:");
			consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
			consoleOutput.println(book.getLoanCount());
			
			consoleOutput.println();
			
		}
		clearWindows(false);
	}
	
	/**
	 * Sets up the library with 5 books.
	 */
	public void setupLibrary() {
		library.add(new LibraryBook("The Twits","Roald Dahl","0123456789",BookType.FICTION,1,"A hideous married couple know an the twits live together in a brick house without windows.",11.99,"theTwits.jpg"));
		library.add(new LibraryBook("The Boy Who Cried Wolf","Aesop","0778778908",BookType.FICTION,1,"A boy tending sheep on a lonely mountain side thinks its fine a fine joke to cry \"Wolf\"",8.99,"theBoyWhoCriedWolf.jpg"));
		library.add(new LibraryBook("The Boy the Mole the Fox and the Horse","Charlie Mackesy","0062976583",BookType.FICTION,1,"A relatable book in this hard time of living",4.99,"theBoyEtc.jpg"));
		library.add(new LibraryBook("The Nigth Circus","Erin Morgenstern","0385534639",BookType.FICTION,1,"Tells the story of a mysterious and magical competition.",8.99,"theNightCircus.jpg"));
		library.add(new LibraryBook("A Brief History of Humankind","Yuval Noah Harari","0062316095",BookType.NON_FICTION,1,"Sapiens explores the history of the human species",13.99,"aBriefHistoryOfHumanKind.jpg"));
		
	}
	
	/**
	 * Sets up the 4 console screens in to fill the whole screen
	 */
	public void setupConsoles() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		// Heights
		int titleHeight = (int) (screenHeight * 0.15);
		int mainHeight = (int) (screenHeight * 0.60);
		int inputHeight = (int) (screenHeight * 0.25);
		
		// Widths
		int leftWidth = (int) (screenWidth * 0.5);
		int rightWidth = screenWidth - leftWidth;
		
		int titleY = 0;
		int mainY = titleHeight;
		int inputY = titleHeight + mainHeight;
		// Sets up a new colour for consoles
		Color lightBlue = new Color(175,200,252);
		
		// Sets up the title bar
		consoleTitle.setSize(screenWidth,titleHeight);
		consoleTitle.setLocation(0,titleY);
		consoleTitle.setVisible(true);
		consoleTitle.setFont(new Font("Courier", Font.PLAIN,15));
		
		// Sets up the image window
		consoleImage.setSize(leftWidth,mainHeight);
		consoleImage.setLocation(0,mainY);
		consoleImage.setVisible(true);
		consoleImage.setFont(new Font("Courier", Font.BOLD,15));
		consoleImage.setBgColour(lightBlue);
		consoleImage.setColour(Color.black);
		
		// Sets up the output window
		consoleOutput.setSize(rightWidth,mainHeight);
		consoleOutput.setLocation(leftWidth,mainY);
		consoleOutput.setVisible(true);
		consoleOutput.setFont(new Font("Courier", Font.PLAIN,15));
		consoleOutput.setBgColour(lightBlue);
		consoleOutput.setColour(Color.black);
		
		// Sets up the input window
		consoleInput.setSize(screenWidth,inputHeight);
		consoleInput.setLocation(0,inputY);
		consoleInput.setVisible(true);
		consoleInput.setFont(new Font("Courier", Font.PLAIN,15));
		consoleInput.setBgColour(lightBlue);
		consoleInput.setColour(Color.black);
	}
	
	/**
	 * Runs a menu
	 */
	public void runMenu() {
		final String[] OPTIONS = new String[] {"List All Books","List Books by Status","Add a Book","Remove a Book","Borrow a Book","Return a Book","Display Ranked List","Exit"};
		Menu menu = new Menu(OPTIONS,"QUBLibrarySoftware",consoleInput);
		
		int input = -1;
		while(input != 8) {
			consoleInput.clear();
			consoleInput.println(menu);
			updateTitleBar("qubLibrarySystem.jpg");
			input = menu.getInput();
			consoleInput.clear();
			
			switch(input) {
			// List all books
			case 1:
				listAllBooks();
				break;
			
			//List Books by Status
			case 2:
				listBooksByStatus();
				break;
			
			//Add a Book
			case 3:
				addABook();
				break;
			
			//Remove a Book
			case 4:	
				removeBook();
				break; 
			
			//Borrow a Book
			case 5:
				borrowBook();
				break;
			
			//Return a Book
			case 6:
				returnBook();
				break;
			
			//Display Ranked List
			case 7:
				displayRankedList();
				break;
			case 8:
				consoleInput.setVisible(false);
				consoleOutput.setVisible(false);
				consoleImage.setVisible(false);
				consoleTitle.setVisible(false);
			}
		}
	}
	
	/**
	 * @param args not used
	 * Main program lists options and uses a switch statement to call each function.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		QUBLibrary app = new QUBLibrary();
		app.setupLibrary();
		app.setupConsoles();
		app.runMenu();

	}

}
