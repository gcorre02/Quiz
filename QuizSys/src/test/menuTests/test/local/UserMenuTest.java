package test.menuTests.test.local;

import menu.UserMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.LoaderInterface;
import persistence.Saver;
import persistence.SaverInterface;
import tools.UserInterface;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class UserMenuTest {
	private UserInterface ui;
	private UserMenu um;
	private LoaderInterface l;
	private SaverInterface s;
	private String user;
	private String source;
	@Before
	public void setUp() throws Exception {
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

	@Test
	public final void testrunMenu() {
		//mock
        when(ui.getUserAnswer(anyString())).thenReturn('D');
		//run
        um.run();
        //test
		verify(ui).printToUser("Logging out, thank you");
	}



	@Test
	public final void testDeleteQuiz() throws IOException {
        //setup
        String newUser = "Sapo";
        s.addUserName(newUser);
        String newQuiz = "sapinhos";
        s.addQuiz(newQuiz,newUser,l.getUserQuizzes());
        UserMenu num = new UserMenu(l,s,ui,newUser);
        //mock
		when(ui.getUserAnswer(anyString())).thenReturn('B','D','D');
		when(ui.readFromUser()).thenReturn("0");
		//run
        num.run();
		num.run();
        //test
        File f = new File(source + File.separator + newUser + File.separator + newQuiz + File.separator + newQuiz + ".txt");
		assertFalse(f.exists());
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
	//TODO this is just a stubbed test for debug
	@Test
	public final void testEditQuiz(){
		
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn("0");
		um.run();
		
		fail("Not yet implemented"); // TODO
	}
}
