package queensLibrary;

import console.Console;

/**
 * A Menu class that has validation for selection
 */
public class Menu {
	private String[] items;
	private String title;
	private Console input;
	
	public Menu(String[] items, String title, Console input) {
		this.items = items;
		this.title = title;
		this.input = input;
	}

	public String[] getItems() {return items;}

	public String getTitle() {return title;}

	/**
	 * Gets an input and makes sure it is within the valid numbers for the menu.
	 * @return The input number
	 */
	public int getInput() {
		int num = -1;
		while (num < 1 || num > this.items.length) {
			try {
				input.print("Please enter an option: ");
				String userInput = this.input.readLn();
				//Checks if the input is empty
				if (userInput.isEmpty())
					throw new Exception("Invalid Input - Option cannot be empty");
				//Checks the input is a number
				try {
					num = Integer.parseInt(userInput);
				}
				catch (Exception f) {
					throw new Exception("Invalid Input - Option must be a number");
				}
				//Checks the input is a valid number
				if(num < 1 || num > this.items.length)
					throw new Exception(String.format("Invalid input - Option not in range (1-%d)", this.items.length));
			}
			catch (Exception e){
				//Outputs appropriate error message
				input.println(e.getMessage());
			}
		}
		input.println();
		return num;
	}
	
	@Override
	/**
	 * @return Returns the menu as a string object
	 */
	public String toString() {
		String output = "";
		output+=title;
		output+="\n-------------\n";
		
		int count = 1;
		for (String item : items) {
			output+=String.format("%d.%s\n",count, item);
			count++;
		}
		return output;
	}
	
}
