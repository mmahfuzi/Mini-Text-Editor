package backend.receiver;

public class State {

	private int selectionStart;
	private int selectionEnd;
	private String bufferContent;
	private Clipboard clipboard;
	
	public State() {
		bufferContent = "";
		clipboard = new Clipboard();
	}
	public State(State state) {
		this.selectionStart = state.selectionStart;
		this.selectionEnd = state.selectionEnd;
		this.bufferContent = state.bufferContent;
		Clipboard tempClipboard = new Clipboard();
		if(clipboard != null)
			tempClipboard.setContents(clipboard.getContents());
		this.clipboard = tempClipboard;
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
}
