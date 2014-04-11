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
	private UserInterface ui;
	private UserMenu um;
	private Loader l;
	private Saver s;
	private String user;
	private String source;
	@Before
	public void setUp() throws Exception {
		//TODO need to write a setup for the file structure
		ui = mock(UserInterface.class);
		source = "testFiles";
		l = new Loader(source);
		s = new Saver(source);
		user = "Gonzo";
		um = new UserMenu(l, s, ui, user);
	}

	@After
	public void tearDown() throws Exception {
		ui = null;
		source = null;
		l = null;
		s = null;
		user = null;
		um = null;
	}

	//TODO this is just a stubbed test for debug
	@Test
	public final void testrunMenu() {
		when(ui.getUserAnswer(anyString())).thenReturn('D');
		um.run();
		fail("Not yet implemented"); // TODO
	}

	@Test(expected = NullPointerException.class)
	public final void testrunMenuNullExceptionDoesntBrakeSystem() {
		String newSource = "unexistingFolder";
		l = new Loader(newSource);
		s = new Saver(newSource);
		um = new UserMenu(l, s, ui, user);
		when(ui.getUserAnswer(anyString())).thenReturn('D');
		um.run();
	}

	//TODO this is just a stubbed test for debug
	@Test
	public final void testDeleteQuiz(){
		when(ui.getUserAnswer(anyString())).thenReturn('B','D','D');
		when(ui.readFromUser()).thenReturn("0");
		um.run();
		um.run();
		fail("Not yet implemented"); // TODO
	}

	//TODO this is just a stubbed test for debug
	@Test
	public final void testCreateQuiz(){
		//creates 3 quizzes
		when(ui.getUserAnswer(anyString())).thenReturn('A','D','A','D','A','D');
		when(ui.readFromUser()).thenReturn("Boats Boats Boats","cars 2", "numbers 2");
		um.run();
		um.run();
		um.run();
		fail("Not yet implemented"); // TODO
	}
}
