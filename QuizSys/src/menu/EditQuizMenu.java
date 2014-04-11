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
			try {
				deleteQuestion();
			} catch (NumberFormatException | IOException e) {
				System.out.println("Couldn't access the requested question, please try again.");
			}
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
		System.out.println("Please enter the number for the question you wish to edit :");
	}

	private void goBack() {
		System.out.println("You will be returned to the previous menu.");
	}

	private void deleteQuestion() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Please enter the number of the question you wish to delete.");
		String question = l.getQuizQuestionsConfig(user, quizName).get(Integer.parseInt(ui.readFromUser()));
		s.removeAQuestion(question, user, quizName);
		//debug
		System.out.println(question+" <<<<WAS PICKED>>>>");
		//debug
	}

	private void createNewQuestion() {
		// TODO Auto-generated method stub
		System.out.println("Please enter a question:");
		String question = ui.readFromUser();
		if(s.addAQuestion(question, user, quizName))
			System.out.println(question + " was added succesfully.");
		else
			System.out.println("Couldnt add the question, please try again later.");
	}
}
