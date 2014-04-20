package test.menuTests.test.local;

import menu.LoginMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import tools.UserInterface;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LoginMenuTest {
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
		um = new LoginMenu(l,s,ui);
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

	@Test
	public void testDeleteUser() throws IOException {
		//setup
        String user = "Removeable User";
        s.addUserName(user);
        //mock
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn(user);
		//run
		um.run();
        //test
        assertFalse(l.getUsernames().contains(user));
	}

	@Test
	public void testDeleteUnexistentUser(){
		//mock
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("Unexistent User");
		//run
		um.run();
        //test
        verify(ui).printToUser("User "+"Unexistent User"+" does not exist.");
	}

	@Test
	public void testAddUser() throws IOException {
		//SetUp
        String newUser = "Beta";
		//mock
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn(newUser);
		//run
		um.run();
        //test
        assertTrue(l.getUsernames().contains(newUser));
	}

	@Test
	public void testAddexistingUser(){
        //Setup
        String existingUser = "Gonzo";
        //mock
        when(ui.getUserAnswer(anyString())).thenReturn('A','D');
        when(ui.readFromUser()).thenReturn(existingUser);
        //run
        um.run();
        //test
        verify(ui).printToUser(existingUser + " already exists");
	}

	@Test
	public void testLogin(){
		//setup
        String userLogin = "Gonzo";
        //mock
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn(userLogin);
		//run
		um.run();
        //test
        verify(ui).printToUser("Welcome " + userLogin);

	}

    @Test
    public void testLoginUnexistentUser(){
        //setup
        String userLogin = "Delta";
        //mock
        when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
        when(ui.readFromUser()).thenReturn(userLogin);
        //run
        um.run();
        //test
        verify(ui).printToUser(userLogin + " does not exist, can't login.");

    }

}
