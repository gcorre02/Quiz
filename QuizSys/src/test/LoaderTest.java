package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import tools.CollectionPrinter;

public class LoaderTest {
	Loader loader;
	@Before
	public void setUp() throws Exception {
		loader = new Loader("TestFiles");
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
	}

	@Test
	public final void testLoader() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUsernames() {
		
		ArrayList<String> usernames = new ArrayList<>();
		try {
			usernames = loader.getUsernames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(usernames.toString());
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
