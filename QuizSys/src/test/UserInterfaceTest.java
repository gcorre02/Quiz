package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import userInterface.UserInterface;

public class UserInterfaceTest {
	UserInterface ui;
	
	@Before
	public void setUp() throws Exception {
		ui = spy(new UserInterface());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFromUser() {
		//just a test to make sure the mock is working
		String userInput = "some words the user inputs on the console";
		when(ui.readFromUser()).thenReturn(userInput);
		//test
		assertEquals(userInput, ui.readFromUser());
	}
	
	@Test
	public void testGetUserAnswer(){
		String userInput = "A";
		String aBunchOfChoices = "please press A, B or C";
		char answer = ui.getUserAnswer();
		//test
		assertEquals(userInput.charAt(0), answer);
	}

}
