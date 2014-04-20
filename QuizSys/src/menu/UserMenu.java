package menu;

import lombok.AllArgsConstructor;
import persistence.LoaderInterface;
import persistence.SaverInterface;
import tools.CollectionTools;
import tools.UserInterface;

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
			ui.printToUser(user + " has no quizzes.");
		}else{
			ui.printToUser("These are your quizzes:");
			ArrayList<String> thisuserSQuizzes = CollectionTools.toArrayList(userQuizzes);
			ui.printToUser(CollectionTools.collectionPrinter('0', thisuserSQuizzes));
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
			ui.printToUser("Couldn't understand the input, please choose again.");
			run();
			break;
		}
	}

	private void goBack() {
		ui.printToUser("Logging out, thank you");
	}

	private void editQuiz() {
		ui.printToUser("Please enter the number for the quiz you wish to edit");
		//find the name for the selected quiz.
		String quizName = l.getUserQuizzes().get(user)[Integer.parseInt(ui.readFromUser())];
		//debug
		ui.printToUser("You picked " + quizName);
		//debug
		EditQuizMenu eqm = new EditQuizMenu(l,s,ui,user,quizName);
		try {
			eqm.run();
		} catch (Exception e) {
			ui.printToUser("Couldn't access the quiz, please try again");
		}
	}

	private void deleteQuiz() {
		ui.printToUser("Please enter the number of the quiz you wish to delete");
		String quizName = l.getUserQuizzes().get(user)[Integer.parseInt(ui.readFromUser())];
		//debug
		ui.printToUser(quizName+" <<<<WAS PICKED>>>>");
		//debug
		if(s.removeQuiz(quizName, user, l.getUserQuizzes()))
			ui.printToUser("Quiz " + quizName + " has been removed successfuly");
		else
			ui.printToUser("Quiz " + quizName + " could not be completely removed. \nThere might be a conflict with the file system. \nPlease try again.");
	}

	private void createNewQuiz() {
		ui.printToUser("Please enter the name of the quiz you want to create");
		String newQuizName = ui.readFromUser();
		if(s.addQuiz(newQuizName, user, l.getUserQuizzes()))
			ui.printToUser(newQuizName + " was successfully created.");
		else
			ui.printToUser(newQuizName + " could not be created.");
	}

}
