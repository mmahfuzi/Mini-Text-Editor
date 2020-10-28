package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import backend.MiniEditorImpl;

public class ComplexCommandsTest {

	MiniEditorImpl editor;
	private int N = 1000;
	
	@Before
	public void initialize() {
		editor = new MiniEditorImpl();
	}
	
	@Test
	public void insertCopyTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorCopy();
		Assert.assertEquals("Test", editor.getBuffer());
	}
	
	@Test
	public void insertPasteTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorPaste();
		Assert.assertEquals("Test", editor.getBuffer());
	}
	
	@Test
	public void insertCopyPasteTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorCopy();
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorPaste();
		Assert.assertEquals("Test", editor.getBuffer());
	}
	
	@Test
	public void copyNTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1, 3);
		editor.editorCopy();
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorCopy();
		Assert.assertEquals("es", editor.getClipboard());
	}
	
	@Test
	public void pasteNTest() {
		Assert.assertEquals("", editor.getBuffer());
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorPaste();
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1,3);
		editor.editorCopy();
		Assert.assertEquals("es", editor.getClipboard());
		editor.editorSelect(0,0);
		editor.editorPaste();
		Assert.assertEquals("esTest", editor.getBuffer());
	}
	
	@Test
	public void cutSelectTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorSelect(1,3);
		editor.editorCut();
		Assert.assertEquals("es", editor.getClipboard());
		Assert.assertEquals("Tt", editor.getBuffer());
	}
	
	@Test
	public void selectCopyTest() {
		Assert.assertEquals("", editor.getBuffer());
		Assert.assertEquals("", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorInsert("Test");
		Assert.assertEquals("", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorSelect(1,3);
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorCopy();
		Assert.assertEquals("es", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorSelect(1,2);
		Assert.assertEquals("e", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
	}
	
	@Test
	public void deleteCopyTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorCopy();
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1,3);
		editor.editorDelete();
		Assert.assertEquals("", editor.getClipboard());
		Assert.assertEquals("Tt", editor.getBuffer());
	}
	
	@Test
	public void deletePasteTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorPaste();
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1,3);
		editor.editorDelete();
		Assert.assertEquals("", editor.getClipboard());
		Assert.assertEquals("Tt", editor.getBuffer());
	}
	
	@Test
	public void undoNTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		for(int i=0; i< (int)(Math.random()*N); i++)
		editor.editorUndo();
		Assert.assertEquals("", editor.getClipboard());
		editor.editorUndo();
		Assert.assertEquals("", editor.getBuffer());
	}
	
	@Test
	public void redoNTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		for(int i=0; i< (int)(Math.random()*N); i++)
		editor.editorRedo();
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorUndo();
		Assert.assertEquals("", editor.getBuffer());
	}
	
	@Test(expected=NullPointerException.class)
	public void emptyBufferNTest() {
		editor.getState().setBufferContent(null);
		for(int i=0; i< (int)(Math.random()*N); i++)
			editor.editorInsert("ABC");
	}
	
	@After
	public void clean() {
		editor = null;
	}
}