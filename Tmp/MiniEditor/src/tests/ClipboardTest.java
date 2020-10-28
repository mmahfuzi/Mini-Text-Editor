package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.receiver.Clipboard;

public class ClipboardTest {
	
	private Clipboard c ;
	
	@Before
	public void initialize() {
		c = new Clipboard();
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
	
	@After
	public void clean() {
		c = null;
	}
}
