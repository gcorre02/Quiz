package userInterface;

public class UserInterface {

	public String readFromUser() {
		return System.console().readLine();
	}

	public char getUserAnswer(String aBunchOfChoices) {
		System.out.println(aBunchOfChoices);
		char response = readFromUser().toUpperCase().charAt(0);
		return response;
	}
	//for mocking
	public char getUserAnswer(String aBunchOfChoices, UserInterface ui) {
		System.out.println(aBunchOfChoices);
		char response = ui.readFromUser().toUpperCase().charAt(0);
		return response;
	}

}
