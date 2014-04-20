package test.menuTests.test.remote;

import menu.EditQuizMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.*;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EditQuizMenuRmiTest {
	private EditQuizMenu eqm;
	private UserInterface ui;
	private LoaderInterface l;
	private SaverInterface s;
    private String source;
    private String user;
    private String quizName;

    @Before
	public void setUp() throws Exception {
	    source = "testFiles";
        l = new LoaderRmiCaller(source);
		s = new SaverRmiCaller(source);
		ui = mock(UserInterface.class);
        user = "Septimus";
        quizName = "Bond Villains";
        eqm = new EditQuizMenu(l, s, ui, user, quizName);
	}

	@After
	public void tearDown() throws Exception {
		l=null;
        s=null;
        source=null;
        ui = null;
        eqm = null;
	}

	@Test
	public final void testRun() {
		//mock
        when(ui.getUserAnswer(anyString())).thenReturn('D');
        //launch
        eqm.run();
        //test
        verify(ui).printToUser("You will be returned to the previous menu.");
	}


	@Test
	public final void testDeleteQuestion() throws IOException{
		//expected
        String expected = l.getQuizQuestionsConfig(user,quizName).get(0);
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
        //actuals
        ArrayList<String> actuals = l.getQuizQuestionsConfig(user, quizName);
		//test
        assertFalse(actuals.contains(expected));
	}

	@Test
	public final void testAddQuestion() throws IOException{
		//expected
        String expected = "What type of moron is Moe Sizlack?";
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn("What type of moron is Moe Sizlack?");
		//exec
		eqm.run();
        //actuals
        ArrayList<String> actuals = l.getQuizQuestionsConfig(user,quizName);
		//test
        assertTrue(actuals.contains(expected));
	}

	@Test
	public final void testEditQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
		//test
        verify(ui).printToUser("Please enter the number for the question you wish to edit :");
    }
}
