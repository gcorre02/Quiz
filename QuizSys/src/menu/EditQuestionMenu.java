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
			Question quStub = new Question(question, new ArrayList<String>(), 100, user, quizName);
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
			try {
				selectRightAnswer();
			} catch (Exception e) {
				System.out.println("Couldn't access the question object. please try again later.");
			}
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
		System.out.println("you'll be taken to the previous menu.");
	}

	private void selectRightAnswer() throws Exception {
		// TODO Auto-generated method stub
		Question qu = l.getQuestionObject(user, quizName, question);
		if(qu.getRightAnswer()==100){
			System.out.println("No right answer has been set up yet");
		}else {
			System.out.println("The current right answer is -> " + qu.getAnswer(qu.getRightAnswer()));
		}
		System.out.println("Please enter the number to identify the new right answer :");
		int newRightAnswer = Integer.parseInt(ui.readFromUser());
		qu.setRightAnswer(newRightAnswer);
		String newRightAnswerStr =""; 
		try{
			newRightAnswerStr = qu.getAnswer(newRightAnswer);
		}catch(Exception e){
			System.out.println("answer does not exist");
			throw new Exception();
		}
		System.out.println("The new right answer is : " + newRightAnswerStr);
		s.saveAQuestionObject(qu);
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
