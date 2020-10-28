package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.receiver.Clipboard;
import backend.receiver.MiniEditor;
import backend.receiver.MiniEditorImpl;

public class MiniEditorTest {
	
	private Clipboard c ;
	private MiniEditor editor;
	
	@Before
	public void initialize() {
		c = new Clipboard();
		editor = new MiniEditorImpl();
	}
	
	@Test
	public void emptyClipBoardTest() {                               // Test for clipboard    
		Assert.assertEquals(true, c.isEmpty());
		c.setContents("Test");
		Assert.assertEquals(false, c.isEmpty());
	}
	
	@Test
	public void contentChangeTest() {                               // Test for change in content
		Assert.assertEquals("", c.getContents());
		c.setContents("Test");
		Assert.assertEquals("Test", c.getContents());
		c.setContents("");
		Assert.assertEquals("", c.getContents());
	}
	
	@Test
	public void bufferTest() {                                     // Test for buffer
		Assert.assertEquals("", editor.getBuffer());
	}
	
	@After
	public void clean() {
		c = null;
	}
}
