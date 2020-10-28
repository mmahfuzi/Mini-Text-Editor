package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.Clipboard;
import backend.MiniEditor;
import backend.MiniEditorImpl;

public class MiniEditorStorageTest {
	
	private Clipboard c ;
	private MiniEditor editor;
	
	@Before
	public void initialize() {
		c = new Clipboard();
		editor = new MiniEditorImpl();
	}
	
	@Test
	public void emptyClipBoardTest() {
		Assert.assertEquals(true, c.isEmpty());
		c.setContents("Test");
		Assert.assertEquals(false, c.isEmpty());
	}
	
	@Test
	public void contentChangeTest() {
		Assert.assertEquals("", c.getContents());
		c.setContents("Test");
		Assert.assertEquals("Test", c.getContents());
		c.setContents("");
		Assert.assertEquals("", c.getContents());
	}
	
	@Test
	public void bufferChangeTest() {
		Assert.assertEquals("", editor.getBuffer());
		editor.editorInsert("Test");
		Assert.assertEquals("Test", editor.getBuffer());
	}
	
	@After
	public void clean() {
		c = null;
	}
}
