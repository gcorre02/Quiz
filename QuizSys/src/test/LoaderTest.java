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
import tools.CollectionPrinter;

public class LoaderTest {
	Loader loader;
	String folder;
	String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
	ArrayList<String> userNames ;
	Saver s;
	@Before
	public void setUp() throws Exception {
		folder = "TestFiles";
		loader = new Loader(folder);
		
		userNames = new ArrayList<>();
		for(String name : names){
			userNames.add(name);
		}
		s = new Saver(folder);
		s.saveUserNames(userNames);
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
		folder = null;
		userNames = null;
		s = null;
	}

	@Test
	public final void testLoader() {
		assertTrue(loader instanceof Loader);
		File f = new File(folder);
		assertTrue(f.exists());
	}

	@Test
	public final void testGetUsernames() throws IOException {
		//setup
		
		
		ArrayList<String> retrievedUserNames = new ArrayList<>();

		retrievedUserNames = loader.getUsernames();
		//debug
		System.out.println(retrievedUserNames.toString());
		//expecteds
		String[] expecteds = userNames.toArray(new String[0]); 
		//actuals
		String[] actuals = retrievedUserNames.toArray(new String[0]); 
		//test
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public final void testGetUserQuizzesMap(){
		//setup
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		Map<String, String[]> userQuizzes = new HashMap<>();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : names){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		s = new Saver(folder);
		s.saveUserQuizzes(userQuizzes);
		//expected
		String[] expecteds = gonzoQuizzes;
		String[] expectedKeys = names;
		//actual
		String[] actuals = loader.getUserQuizzes().get("Gonzo");
		String[] actualKeys =loader.getUserQuizzes().keySet().toArray(new String[0]);
		//debug
		System.out.println(CollectionPrinter.printMap(loader.getUserQuizzes()));
		//test
		assertArrayEquals(expecteds, actuals);
		assertTrue(CollectionPrinter.compareTwoArrays(actualKeys, expectedKeys));
	}



}
