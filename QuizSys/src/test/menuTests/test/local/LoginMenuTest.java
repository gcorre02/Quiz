package test.menuTests.test.local;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Map;

import menu.LoginMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import tools.UserInterface;

public class LoginMenuTest {
	LoginMenu um;
	UserInterface ui;
	Loader l;
	Saver s;
	String source;
	ArrayList<String> userNames;
	
	@Before
	public void setUp() throws Exception {
		source = "testFiles";
		ui = mock(UserInterface.class);
		l = new Loader(source);
		s = new Saver(source);
		um = spy(new LoginMenu(l,s,ui));
		//file struture setup
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		userNames = new ArrayList<>();
		for(String name : names){
			userNames.add(name);
		}
		s.saveUserQuizzes(generateGonzoQuizzes());
	}
	
	private Map<String, String[]> generateGonzoQuizzes() {
		Map<String, String[]> userQuizzes = l.getUserQuizzes();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : userNames){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		return userQuizzes;
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
	public void testLoginUserWithNoQuizzes(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');//TODO needs to escape
		when(ui.readFromUser()).thenReturn("Beta");
		//debug
		um.run();

	}
	//TODO make independent and a test
	@Test
	public void testLoginUnexistentUser(){
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');//TODO needs to escape
		when(ui.readFromUser()).thenReturn("Delta");
		//debug
		um.run();
	}
	//TODO make independent and a test
	@Test
	public void testLoginUnexistentSystemFolder(){
		//setup
		String newSource = "folderthatainthere";
		l = new Loader(newSource);
		s = new Saver(newSource);
		um = new LoginMenu(l, s, ui);
		//setup TODO <setup test> need to create the whole structure
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');//TODO needs to escape
		when(ui.readFromUser()).thenReturn("Delta");
		//debug
		System.out.println("<<<<<<TEST FOR UNEXISTENT SYSTEM FOLDER>>>>>>");
		um.run();
	}
}
