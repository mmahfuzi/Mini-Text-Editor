package backend.receiver;

import java.util.ArrayList;
import java.util.List;


public class State implements Subject{

	private int selectionStart;
	private int selectionEnd;
	private String bufferContent;
	private Clipboard clipboard;
	private List<Observer> observers;
	
	public State() {
		bufferContent = "";
		clipboard = new Clipboard();
		observers = new ArrayList<Observer>();
	}
	public State(State state) {
		this.selectionStart = state.selectionStart;
		this.selectionEnd = state.selectionEnd;
		this.bufferContent = state.bufferContent;
		Clipboard tempClipboard = new Clipboard();
		if(clipboard != null)
			tempClipboard.setContents(clipboard.getContents());
		this.clipboard = tempClipboard;
		this.observers = state.observers;
		notifyObservers();
	}
	public String getBufferContent() {
		return bufferContent;
	}
	public void setBufferContent(String bufferContent) {
		this.bufferContent = bufferContent;
	}
	public int getSelectionStart() {
		return selectionStart;
	}
	public void setSelectionStart(int selectionStart) {
		this.selectionStart = selectionStart;
	}
	public int getSelectionEnd() {
		return selectionEnd;
	}
	public void setSelectionEnd(int selectionEnd) {
		this.selectionEnd = selectionEnd;
	}
	public Clipboard getClipboard() {
		return clipboard;
	}
	
	@Override
	public void register(Observer observer) {
		observers.add(observer);		
	}
	@Override
	public void unRegister(Observer observer) {
		observers.remove(observer);
	}
	@Override
	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(bufferContent, bufferContent.substring(selectionStart, selectionEnd) + "[" + selectionStart + "," + selectionEnd + "]");
		}
	}
}
