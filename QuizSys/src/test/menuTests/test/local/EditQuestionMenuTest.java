package test.menuTests.test.local;

import menu.EditQuestionMenu;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.LoaderInterface;
import persistence.Saver;
import persistence.SaverInterface;
import tools.UserInterface;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditQuestionMenuTest {

	private LoaderInterface l;
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
        SaverInterface s = new Saver(source);
		l = new Loader(source);
		ui = mock(UserInterface.class);

        questionString = "Who is the sexiest bond villain?";
        eqm = new EditQuestionMenu(questionString,l, s, ui, newUser, newquizName);
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
		int actual = l.getQuestionObject(newUser, newquizName, questionString).getRightAnswer();
		//test
		assertEquals(expected, actual);
	}
	@Test
	public final void testDeleteAnswer() throws IOException {
		//expected
		String expected = l.getQuestionObject(newUser, newquizName, questionString).getAnswer(1);
		//Setup
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("1");
		eqm.run();
		//actual
		//test
		assertFalse(l.getQuestionObject(newUser, newquizName, questionString).getAnswers().contains(expected));
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
		String actual = l.getQuestionObject(newUser, newquizName, questionString).getAnswer(3);
		//test
		assertEquals(expected, actual);
	}
}
