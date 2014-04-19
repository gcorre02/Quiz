package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import userInterface.UserInterface;

public class UserInterfaceTest {
	UserInterface ui;
	UserInterface mockedUserInterface;
	@Before
	public void setUp() throws Exception {
		ui = new UserInterface();
		mockedUserInterface = mock(UserInterface.class);
	}

	@After
	public void tearDown() throws Exception {
        ui = null;
        mockedUserInterface = null;
	}

	@Test
	public void testReadFromUser() {
		//just a test to make sure the mock is working
		String userInput = "some words the user inputs on the console";
		when(mockedUserInterface.readFromUser()).thenReturn(userInput);
		//test
		assertEquals(userInput, mockedUserInterface.readFromUser());
	}
	
	@Test
	public void testGetUserAnswer(){
		String userInput = "A";
		when(mockedUserInterface.readFromUser()).thenReturn(userInput);
		String aBunchOfChoices = "please press A, B or C";
		char answer = ui.getUserAnswer(aBunchOfChoices, mockedUserInterface);
		//test
		assertEquals(userInput.charAt(0), answer);
	}

}
