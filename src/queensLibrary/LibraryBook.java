package queensLibrary;

/**
 * A subclass of Book that adds id, status, image and loan count.
 */
public class LibraryBook extends Book implements Lendable {
	private int id;
	private static int nextId;
	private BookStatus status;
	private String image;
	private int loanCount;
	
	public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary, double price, String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.id = nextId;
		nextId++;
		this.status = BookStatus.AVAILABLE;
		this.image = coverImage;
		this.loanCount = 0;
	}

	public void setStatus(BookStatus status) {this.status = status;}

	public void setLoanCount(int count) {
		this.loanCount = count;
		}
	
	public int getId() {return this.id;}
	
	public int getLoanCount() {return this.loanCount;}
	
	public BookStatus getStatus() {return status;}
	
	public String getImage() {return this.image; }
	
	@Override
	public String toString() {
		return "ID:" + this.id + ", " + super.toString() + ", Book Status: \"" + this.status + "\", Loan Count:" + this.loanCount;
	}
	
	@Override
	/**
	 * Checks if the book is available and if it is the book is checked out and the method returns true
	 */
	public boolean checkout() {
		if (this.status == BookStatus.AVAILABLE) {
			this.status = BookStatus.ON_LOAN;
			this.loanCount += 1;
			return true;
		}
		return false;
	}
	
	@Override
	/**
	 * Checks if the book is on loan and if it is the book is checked in and the method returns true
	 */
	public boolean checkIn() {
		if (this.status == BookStatus.ON_LOAN) {
			this.status = BookStatus.AVAILABLE;
			return true;
		}
		return false;
	}

}
