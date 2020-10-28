package backend;

/**
 * Interface used for observer design pattern
 * @author mukrram
 *
 */
public interface Observer {

	public void update(String bufferText, String selectionText); // Method to receive updates
}
