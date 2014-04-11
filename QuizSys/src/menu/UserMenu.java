package menu;

import java.util.ArrayList;

import persistence.Loader;
import persistence.Saver;
import tools.CollectionPrinter;
import userInterface.UserInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMenu {
	private Loader l;
	private Saver s;
	private UserInterface ui;
	private String user;

	public void run(){
		//TODO <need to check if user has any quizzes first>
		
		String[] userQuizzes = l.getUserQuizzes().get(user);
		if(userQuizzes == null){
			System.out.println(user + " has no quizzes.");
		}else{
			System.out.println("These are your quizzes:");
			ArrayList<String> thisuserSQuizzes = CollectionPrinter.toArrayList(userQuizzes);
			System.out.println(CollectionPrinter.collectionPrinter('0', thisuserSQuizzes));
		}
		
		//TODO <implement the rest>
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("Create a new Quizz");
		menuItems.add("Delete an existing Quizz");
		menuItems.add("Edit a Quizz");
		menuItems.add("Go back to login");
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
			createNewQuiz();
			run();
			break;
		case 'B':
			deleteQuiz();
			run();
			break;
		case 'C':
			editQuiz();
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
		System.out.println("Logging out, thank you");
	}

	private void editQuiz() {
		// TODO Auto-generated method stub
		System.out.println("Edit a quiz menu");
	}

	private void deleteQuiz() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the name of the quiz you wish to delete");
	}

	private void createNewQuiz() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the name of the quiz you want to create");
	}

	
	/*
	 * public void run(){
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("New user");
		menuItems.add("Delete User");
		menuItems.add("Login");
		menuItems.add("Close Program");
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
			createNewUser();
			run();
			break;
		case 'B':
			deleteUser();
			run();
			break;
		case 'C':
			login();
			run();
			break;
		case 'D':
			closeProgram();
			break;
		default:
			System.out.println("Couldn't understand the input, please choose again.");
			run();
			break;
		}
	}
	 */
}