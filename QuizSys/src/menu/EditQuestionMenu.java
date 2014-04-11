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
		char choice = ui.getUserAnswer(menu);
		//debug
		System.out.println(choice);
		//\debug
		switch(choice){
		case 'A':
			createNewAnswer();
			run();
			break;
		case 'B':
			deleteAnswer();
			run();
			break;
		case 'C':
			chooseRightAnswer();
			run();
			break;
		case 'D':
			goBack();
			break;
		default:
			System.out.println("Couldn't understand the input, please choose again.");
			run();
			break;
		}
	}

	private void goBack() {
		// TODO Auto-generated method stub
		System.out.println("you'll be taken to the previous menu.");
	}

	private void chooseRightAnswer() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the key to the new right answer :");
	}

	private void deleteAnswer() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the number for the answer you wish to delete:");
	}

	private void createNewAnswer() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the new answer :");
	}
}
