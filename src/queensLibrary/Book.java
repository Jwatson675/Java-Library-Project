package queensLibrary;


/**
 * A simple book with basic validation for the constructor.
 */
public class Book {
	private String title;
	private String author;
	private String isbn;
	private BookType type;
	private int edition;
	private String summary;
	private double price;
	
	/**
	 * Checks that the given string only contains digits
	 * @param digits
	 * @return true or false
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
	
	public Book(String title, String author, String isbn, BookType type, int edition, String summary, double price) {
		if (title.length() >= 5 && title.length() <=40)
			this.title = title;
		else
			this.title = "No Title";
		
		if (author.length() >= 5 && author.length() <=40)
			this.author = author;
		else
			this.author = "No Author";
		
		if (isbn.length() == 10 && allDigits(isbn))
			this.isbn = isbn;
		else
			this.isbn = "No ISBN";
		
		this.type = type;
		
		if (edition >= 1)
			this.edition = edition;
		else
			this.edition = 1;
		
		if (summary.length() >= 20 && summary.length() <= 150)
			this.summary = summary;
		else
			this.summary = "No Summary";
		
		if (price >= 0.0)
			this.price = price;
		else
			this.price = 0.0;
		
	}

	public String getTitle() {return title;}

	public String getAuthor() {return author;}

	public double getPrice() {return price;}

	@Override
	public String toString() {
		return "Title:\"" + title + "\", Author:\"" + author + "\", ISBN:" + isbn + ", Type:\"" + type + "\", Edition:" + edition + ", Summary:\"" + summary + "\", Price:" + String.format("£%.2f", price);
	}
	
	
	

	
}
