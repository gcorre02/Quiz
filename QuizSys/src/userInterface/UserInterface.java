package userInterface;

public class UserInterface {
    //TODO improve the regex, to accept most inputs.
	public String readFromUser() {
		String input = System.console().readLine();
        input = input.replaceAll("\"","");
        return input;
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
