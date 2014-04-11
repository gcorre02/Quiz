package menu;

import java.io.IOException;
import java.util.ArrayList;

import persistence.Loader;
import persistence.Saver;
import quizData.Question;
import tools.CollectionPrinter;
import userInterface.UserInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditQuestionMenu {
	private String question;
	private Loader l;
	private Saver s;
	private UserInterface ui;
	private String user;
	private String quizName;


	public void run() {
		try{
			Question qu = l.getQuestionObject(user, quizName, question);
			ArrayList<String> answers = qu.getAnswers();
			qu = null;
			System.out.println(CollectionPrinter.collectionPrinter('0', answers));
		}catch(Exception e){
			System.out.println(question+" has no answers yet");
		}
		//TODO <implement the rest>
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("Create a new Answer");
		menuItems.add("Delete an existing Answer");
		menuItems.add("Choose the right answer");
		menuItems.add("Go back to Question choice");
		String menu = CollectionPrinter.collectionPrinter('S', menuItems);
		runMenu(menu);

	}


	private void runMenu(String menu) {
		// TODO Auto-generated method stub
		
	}
}
