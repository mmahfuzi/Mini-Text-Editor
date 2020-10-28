package backend.receiver;

/**
 * Class to manage contents of clipboard of Minieditor.
 * @author mukrram
 *
 */
public class Clipboard {

	private String contents;
	
	public Clipboard() {
		contents = "";
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public boolean isEmpty() {
		return contents.isEmpty();
	}
}
