package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		String newUser = "Guy Fawlkes";
		assertTrue(s.addUserName(newUser));
		File f = new File(folder + File.separator + newUser);
		assertTrue(f.exists());
	}
	@Test
	public final void testDeleteUserNames() throws IOException {
		String newUser = "DeleteableUser";
		s.addUserName(newUser);
		assertTrue(s.deleteUser(newUser));
		File f = new File(folder + File.separator + newUser);
		assertFalse(f.exists());
	}
	
	//@Test
	public final void testSaveUserQuizzes() {
		fail("Not yet implemented"); // TODO
	}

	//@Test
	public final void testSaveQuiz() {
		fail("Not yet implemented"); // TODO
	}

}
