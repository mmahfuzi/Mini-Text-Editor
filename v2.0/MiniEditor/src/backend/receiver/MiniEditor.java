package backend.receiver;

public interface MiniEditor
{
	public String getBuffer();
	public String getSelection();
	public String getClipboard();
	public void editorInsert(String substring);
	public void editorSelect(int start, int stop);
	public void editorCopy();
	public void editorCut();
	public void editorPaste() ;
	public void editorDelete();
	public void startRecord();
	public void stopRecord();
	public void replay();
}
