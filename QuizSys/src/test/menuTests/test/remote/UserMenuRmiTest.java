package test.menuTests.test.remote;

import menu.UserMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.*;
import tools.UserInterface;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserMenuRmiTest {
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
		l = new LoaderRmiCaller(source);
		s = new SaverRmiCaller(source);
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
        Map<String, String[]> quizMap = l.getUserQuizzes();
        s.addQuiz(newQuiz,newUser,quizMap);
        UserMenu num = new UserMenu(l,s,ui,newUser);
        //mock
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("0");
		//run
        num.run();
        //test
        File f = new File(source + File.separator + newUser + File.separator + newQuiz + File.separator + newQuiz + ".txt");
		assertFalse(f.exists());
	}

	@Test
	public final void testCreateQuiz() throws IOException {
        //setup
        String newUser = "Johnny Q";
        s.addUserName(newUser);
        String newQuiz = "Boats Boats Boats";
        String newQuiz1 = "cars 2";
        String newQuiz2 = "numbers 2";
        UserMenu num = new UserMenu(l,s,ui,newUser);
        //creates 3 quizzes
		when(ui.getUserAnswer(anyString())).thenReturn('A','D','A','D','A','D');
		when(ui.readFromUser()).thenReturn(newQuiz, newQuiz1, newQuiz2);
		//run
        num.run();
		num.run();
		num.run();
        File f = new File(source + File.separator + newUser + File.separator + newQuiz + File.separator + newQuiz + ".txt");
        File f1 = new File(source + File.separator + newUser + File.separator + newQuiz1 + File.separator + newQuiz1 + ".txt");
        File f2 = new File(source + File.separator + newUser + File.separator + newQuiz2 + File.separator + newQuiz2 + ".txt");
        //test
        assertTrue(f.exists());
        assertTrue(f1.exists());
        assertTrue(f2.exists());
	}
	//TODO this is just a stubbed test for debug
	@Test
	public final void testEditQuiz(){
		//mock
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn("0");
		//run
        um.run();
		//test
        verify(ui).printToUser("You picked " + "numbers");
	}
}
