package menu;

import lombok.AllArgsConstructor;
import persistence.LoaderInterface;
import persistence.SaverInterface;
import tools.CollectionTools;
import userInterface.UserInterface;

import java.util.ArrayList;

@AllArgsConstructor
public class UserMenu {
    private LoaderInterface l;
    private SaverInterface s;
	private UserInterface ui;
	private String user;

	public void run() throws NullPointerException{
		//TODO <need to check if user has any quizzes first>

		String[] userQuizzes = l.getUserQuizzes().get(user);
		if(userQuizzes == null){
			System.out.println(user + " has no quizzes.");
		}else{
			System.out.println("These are your quizzes:");
			ArrayList<String> thisuserSQuizzes = CollectionTools.toArrayList(userQuizzes);
			System.out.println(CollectionTools.collectionPrinter('0', thisuserSQuizzes));
		}

		//TODO <implement the rest>
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("Create a new Quizz");
		menuItems.add("Delete an existing Quizz");
		menuItems.add("Edit a Quizz");
		menuItems.add("Go back to login");
		String menu = CollectionTools.collectionPrinter('S', menuItems);
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
		System.out.println("Logging out, thank you");
	}

	private void editQuiz() {
		System.out.println("Please enter the number for the quiz you wish to edit");
		//find the name for the selected quiz.
		String quizName = l.getUserQuizzes().get(user)[Integer.parseInt(ui.readFromUser())];
		//debug
		System.out.println(quizName+" <<<<WAS PICKED>>>>");
		//debug
		EditQuizMenu eqm = new EditQuizMenu(l,s,ui,user,quizName);
		try {
			eqm.run();
		} catch (Exception e) {
			System.out.println("Couldn't access the quiz, please try again");
		}
	}

	private void deleteQuiz() {
		System.out.println("Please enter the number of the quiz you wish to delete");
		String quizName = l.getUserQuizzes().get(user)[Integer.parseInt(ui.readFromUser())];
		//debug
		System.out.println(quizName+" <<<<WAS PICKED>>>>");
		//debug
		if(s.removeQuiz(quizName, user, l.getUserQuizzes()))
			System.out.println("Quiz " + quizName + " has been removed successfuly");
		else
			System.out.println("Quiz " + quizName + " could not be completely removed. \nThere might be a conflict with the file system. \nPlease try again.");
	}

	private void createNewQuiz() {
		System.out.println("Please enter the name of the quiz you want to create");
		String newQuizName = ui.readFromUser();
		if(s.addQuiz(newQuizName, user, l.getUserQuizzes()))
			System.out.println(newQuizName + " was successfully created.");
		else
			System.out.println(newQuizName + " could not be created.");
	}

}
