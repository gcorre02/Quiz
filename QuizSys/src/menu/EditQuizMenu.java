package menu;

import java.io.IOException;
import java.util.ArrayList;

import persistence.Loader;
import persistence.Saver;
import tools.CollectionPrinter;
import userInterface.UserInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditQuizMenu {
	private Loader l;
	private Saver s;
	private UserInterface ui;
	private String user;
	private String quizName;
	
	public void run() throws NullPointerException{
		//TODO <need to check if quiz has any questions first>

		ArrayList<String> userQuestions;
		try {
			userQuestions = l.getQuizQuestionsConfig(user, quizName);
			if(userQuestions == null){
				System.out.println(quizName + " has no questions.");
			}else{
				System.out.println("Current Questions:");
				System.out.println(CollectionPrinter.collectionPrinter('0', userQuestions));
			}
		} catch (IOException e) {
			System.out.println("Quiz has no questions.");
		}
		

		//TODO <implement the rest>
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("Create a new Question");
		menuItems.add("Delete an existing Question");
		menuItems.add("Edit a Question");
		menuItems.add("Go back to Quiz choice");
		String menu = CollectionPrinter.collectionPrinter('S', menuItems);
		runMenu(menu);
	}

	public void runMenu(String menu){
		char choice = ui.getUserAnswer(menu);
		//debug
		System.out.println(choice);
		//\debug
		switch(choice){
		case 'A':
			createNewQuestion();
			run();
			break;
		case 'B':
			deleteQuestion();
			run();
			break;
		case 'C':
			editQuestion();
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

	private void editQuestion() {
		// TODO Auto-generated method stub
		
	}

	private void goBack() {
		// TODO Auto-generated method stub
		
	}

	private void deleteQuestion() {
		// TODO Auto-generated method stub
		
	}

	private void createNewQuestion() {
		// TODO Auto-generated method stub
		
	}
}
