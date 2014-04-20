package menu;

import lombok.AllArgsConstructor;
import persistence.LoaderInterface;
import persistence.SaverInterface;
import quizData.Quiz;
import tools.CollectionTools;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
public class EditQuizMenu {
    private LoaderInterface l;
    private SaverInterface s;
	private UserInterface ui;
	private String user;
	private String quizName;
	
	public void run() throws NullPointerException{
		//TODO <need to check if quiz has any questions first>

		ArrayList<String> userQuestions;
		try {
			userQuestions = l.getQuizQuestionsConfig(user, quizName);
			if(userQuestions == null){
				ui.printToUser(quizName + " has no questions.");
			}else{
				ui.printToUser("Current Questions:");
				ui.printToUser(CollectionTools.collectionPrinter('0', userQuestions));
			}
		} catch (IOException e) {
			Quiz quiz = new Quiz(quizName, user);
			quiz.setQuizQuestions(new ArrayList<String>());
			s.saveQuiz(quiz);
			ui.printToUser("Quiz is empty.");
		}
		

		//TODO <implement the rest>
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("Create a new Question");
		menuItems.add("Delete an existing Question");
		menuItems.add("Edit a Question");
		menuItems.add("Go back to Quiz choice");
		String menu = CollectionTools.collectionPrinter('S', menuItems);
		runMenu(menu);
	}

	public void runMenu(String menu){
		char choice = ui.getUserAnswer(menu);

		switch(choice){
		case 'A':
			createNewQuestion();
			run();
			break;
		case 'B':
			try {
				deleteQuestion();
			} catch (NumberFormatException | IOException e) {
				ui.printToUser("Couldn't access the requested question, please try again.");
			}
			run();
			break;
		case 'C':
			try {
				editQuestion();
			} catch (Exception e) {
				ui.printToUser("Couldn't access the question, please try again later.");
			}
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

	private void editQuestion() throws NumberFormatException, IOException {
		ui.printToUser("Please enter the number for the question you wish to edit :");
		String question = l.getQuizQuestionsConfig(user, quizName).get(Integer.parseInt(ui.readFromUser()));
		EditQuestionMenu ecm =  new EditQuestionMenu(question, l, s, ui, user, quizName);
		ecm.run();
	}

	private void goBack() {
		ui.printToUser("You will be returned to the previous menu.");
	}

	private void deleteQuestion() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		ui.printToUser("Please enter the number of the question you wish to delete.");
		String question = l.getQuizQuestionsConfig(user, quizName).get(Integer.parseInt(ui.readFromUser()));
		s.removeAQuestion(question, user, quizName);
		//debug
		ui.printToUser(question+" <<<<WAS PICKED>>>>");
		//debug
	}

	private void createNewQuestion() {
		// TODO Auto-generated method stub
		ui.printToUser("Please enter a question:");
		String question = ui.readFromUser();
		if(s.addAQuestion(question, user, quizName))
			ui.printToUser(question + " was added succesfully.");
		else
			ui.printToUser("Couldnt add the question, please try again later.");
	}
}
