package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.receiver.MiniEditor;
import backend.receiver.MiniEditorImpl;

/**
 * To test single commands on miniEditor.
 * @author mukrram
 *
 */
public class SingleOperationTest {

	MiniEditor editor;
	
	@Before
	public void initialize() {
		editor = new MiniEditorImpl();
	}
	
	@Test
	public void insertTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
	}
	
	@Test
	public void copyTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1, 3);
		editor.editorCopy();
		Assert.assertEquals("es", editor.getClipboard());
	}
	
	@Test
	public void pasteTest() {
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
	public void cutTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1,3);
		editor.editorCut();
		Assert.assertEquals("es", editor.getClipboard());
		Assert.assertEquals("Tt", editor.getBuffer());
	}
	
	@Test
	public void selectTest() {
		Assert.assertEquals("", editor.getBuffer());
		Assert.assertEquals("", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorInsert("Test");
		Assert.assertEquals("", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorSelect(1,3);
		Assert.assertEquals("es", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
		editor.editorSelect(1,2);
		Assert.assertEquals("e", editor.getSelection().substring(0, editor.getSelection().indexOf('[')));
	}
	
	@Test
	public void deleteTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
		editor.editorSelect(1,3);
		editor.editorDelete();
		Assert.assertEquals("", editor.getClipboard());
		Assert.assertEquals("Tt", editor.getBuffer());
	}
	
	@After
	public void clean() {
		editor = null;
	}
}
