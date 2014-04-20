package test.menuTests.test.local;

import menu.EditQuizMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.LoaderInterface;
import persistence.Saver;
import persistence.SaverInterface;
import tools.UserInterface;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class EditQuizMenuTest {
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
        l = new Loader(source);
		s = new Saver(source);
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
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRunMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditQuizMenu() {
		fail("Not yet implemented"); // TODO
	}
	//stub question dellete, needs a propper test
	@Test
	public final void testDeleteQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}

	//stub question dellete, needs a propper test
	@Test
	public final void testAddQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn("What type of moron is Moe Sizlack?");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}

	//stub question dellete, needs a propper test
	@Test
	public final void testEditQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}
}
