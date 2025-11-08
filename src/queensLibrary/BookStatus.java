package queensLibrary;

// Enum to represent the status of a book in a library
public enum BookStatus {
	AVAILABLE("Available"),
	ON_LOAN("On Loan"),
	WITHDRAWN("Withdrawn");
	
	private String typeStr;
	
	private BookStatus(String status) {
		this.typeStr = status;
	}
	
	@Override
	public String toString() {
		return this.typeStr;
	}
}
