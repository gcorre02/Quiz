package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import menu.LoginMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import userInterface.UserInterface;

public class UserMenuTest {
	LoginMenu um;
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
		um = spy(new LoginMenu(l,s,ui));
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
	//TODO make independent and a test
	@Test
	public void testDeleteUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("Gonzo");
		//debug
		um.run();

	}
	//TODO make independent and a test
	@Test
	public void testDeleteUnexistentUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("Gonzo");
		//debug
		um.run();
	}
	//TODO make independent and a test
	@Test
	public void testAddUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn("Beta");
		//debug
		um.run();

	}
	//TODO make independent and a test
	@Test
	public void testAddUnexistentUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn("Beta");
		//debug
		um.run();
	}

	//TODO make independent and a test
	@Test
	public void testLogin(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('C','D');
		when(ui.readFromUser()).thenReturn("Gonzo");
		//debug
		um.run();

	}
	//TODO make independent and a test
	@Test
	public void testLoginUnexistentUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('C','D');
		when(ui.readFromUser()).thenReturn("Delta");
		//debug
		um.run();
	}

}
