package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;

public class SaverTest {
	ArrayList<String> userNames;
	Saver s;
	String folder;
	@Before
	public void setUp() throws Exception {
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		userNames = new ArrayList<>();
		for(String name : names){
			userNames.add(name);
		}
		folder = "testFiles";
		s = new Saver(folder);
		
	}

	@After
	public void tearDown() throws Exception {
		//s.deleteFolder(folder); TODO <make it available for general testing>
		userNames = null;
		s = null;
		folder = null;
	}

	@Test
	public final void testSaver() {
		//setup
		String sourceFolder = s.getSource();
		File file = new File(sourceFolder);
		//expected
		String expected = folder;
		//actual
		String actual = sourceFolder;
		//test
		assertTrue(file.exists());
		assertEquals(expected, actual);
	}

	@Test
	public final void testSaveUserNames() {
		assertTrue(s.saveUserNames(userNames));
	}
	//these two tests are trying to be handled concurrently by the system, which forces them to wait for each other.
	@Test
	public final void testAddUserNames() throws IOException {
	//	s.saveUserNames(userNames);
		String newUser = "Guy Fawlkes";
		assertTrue(s.addUserName(newUser));
		File f = new File(folder + File.separator + newUser);
		assertTrue(f.exists());
	}
	@Test
	public final void testDeleteUserNames() throws IOException {
		//s.saveUserNames(userNames);
		String newUser = "DeleteableUser";
		s.addUserName(newUser);
		assertTrue(s.deleteUser(newUser));
		File f = new File(folder + File.separator + newUser);
		assertFalse(f.exists());
	}
	
	@Test
	public final void testSaveUserQuizzes() {
		//setup
		Map<String, String[]> userQuizzes = new HashMap<>();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : userNames){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		//test
		assertTrue(s.saveUserQuizzes(userQuizzes));
	}
	
	@Test
	public final void testAddQuizz(){
		//setup
		Map<String, String[]> userQuizzes = new HashMap<>();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : userNames){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		String newQuiz = "Sexy Sailors of the 20th Century";
		String user = "Bartolomeu";
		s.addQuiz(newQuiz, user, userQuizzes);
		//expected
		String expected = newQuiz;
		//actual
		Loader l = new Loader(folder);
		String actual = l.getUserQuizzes().get(user)[0];
		//test
		assertEquals(expected, actual);
	}
	//@Test
	public final void testSaveQuiz() {
		fail("Not yet implemented"); // TODO
	}

}
