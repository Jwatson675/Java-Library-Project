package queensLibrary;

// Enum to represent the type of book a book is
public enum BookType {
	FICTION("Fiction"),
	NON_FICTION("Non Fiction"),
	REFERENCE("Reference");
	
	private String typeStr;
	
	private BookType(String type) {
		this.typeStr = type;
	}
	
	@Override
	public String toString() {
		return this.typeStr;
	}
}
