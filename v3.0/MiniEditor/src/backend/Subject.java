package backend;

/**
 * Interface used for observer design pattern
 * @author mukrram
 *
 */
public interface Subject {
	
	public void register(Observer observer);
	public void unRegister(Observer observer);
	public void notifyObservers();
}
