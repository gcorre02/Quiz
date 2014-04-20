package test.menuTests.test.local;

import menu.EditQuestionMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import quizData.Question;
import quizData.Quiz;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditQuestionMenuTest {

	private Loader l;
	private Saver s;
	private UserInterface ui;
	private EditQuestionMenu eqm;
	private String questionString;
    private String newUser;
    private String newquizName;

    @Before
	public void setUp() throws Exception {
		//TODO need to setup the file system.
        newquizName = "Another Bond";
        newUser = "Guy Fawlkes";

        String source = "testFiles";
		s = new Saver(source);
		l = new Loader(source);
		ui = mock(UserInterface.class);

        questionString = "Who is the sexiest bond villain?";
        eqm = new EditQuestionMenu(questionString,l, s, ui, newUser, newquizName);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testRun() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditQuestionMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testChangeRightAnswerInt() throws IOException {
		//Setup
		when(ui.getUserAnswer(anyString())).thenReturn('C','D');
		when(ui.readFromUser()).thenReturn("0");
		eqm.run();
		//expected
		int expected = 0;
		//actual
		int actual = l.getQuestionObject(user, quizName, questionString).getRightAnswer();
		//test
		assertEquals(expected, actual);
	}
	@Test
	public final void testDeleteAnswer() throws IOException {
		//expected
		String expected = l.getQuestionObject(user, quizName, questionString).getAnswer(1);
		//Setup
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("1");
		eqm.run();
		//actual
		//test
		assertFalse(l.getQuestionObject(user, quizName, questionString).getAnswers().contains(expected));
	}

	@Test
	public final void testCreateAnswer() throws IOException {
		//expected
		String expected = "Dr. NO";
		//Setup
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn(expected);
		eqm.run();
		
		//actual
		String actual = l.getQuestionObject(user, quizName, questionString).getAnswer(4);
		//test
		assertEquals(expected, actual);
	}
}
