package backend;

/**
 * Class created to manage clip board for Mini text editor
 * @author mukrram
 *
 */
public class Clipboard {

	private String contents;                       // Contents of the clip
	
	public Clipboard() {
		contents = "";
	}
	
	public String getContents() {                  // Getter for Clipboard
		return contents;
	}
	
	public void setContents(String contents) {     // Setter for Clipboard
		this.contents = contents;
	}
	
	public boolean isEmpty() {                     // Method to tell for emptiness of clip board.
		return contents.isEmpty();
	}
}
