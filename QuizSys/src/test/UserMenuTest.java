package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import menu.UserMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import userInterface.UserInterface;

public class UserMenuTest {
	UserMenu um;
	UserInterface ui;
	Loader l;
	Saver s;
	String source;
	
	@Before
	public void setUp() throws Exception {
		source = "testFiles";
		ui = mock(UserInterface.class);
		l = new Loader(source);
		s = new Saver(source);
		um = spy(new UserMenu(l,s,ui));
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		ui = null;
		l = null;
		s = null;
		um = null;
	}

	@Test
	public void testRunShutDown() {
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('D','A','B','C','D');
		//debug
		um.run();
		//test
		verify(ui, times(1)).getUserAnswer(anyString());
	}

}
