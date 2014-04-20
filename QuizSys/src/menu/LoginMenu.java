package menu;

import lombok.AllArgsConstructor;
import persistence.LoaderInterface;
import persistence.SaverInterface;
import tools.CollectionTools;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles all interactions between the user and the user data.
 * Also calls the next menu.
 * 
 * TODO<implement> change username to a different name, without compromising it's dependencies!!!
 * 
 * @author Guilherme
 *
 */
@AllArgsConstructor
public class LoginMenu {
    private LoaderInterface l;
    private SaverInterface s;
	private UserInterface ui;

    /**
     * Presents the user with choices and launches the associated methods for those choices.
     * Also loops through utilization until 'D' is called.
     *
     */
	public void run(){
		ArrayList<String> menuItems = new ArrayList<>();
		menuItems.add("New user");
		menuItems.add("Delete User");
		menuItems.add("Login");
		menuItems.add("Close Program");
		String menu = CollectionTools.collectionPrinter('S', menuItems);
		runMenu(menu);
	}

	private void runMenu(String menu){
		char choice = ui.getUserAnswer(menu);

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
			ui.printToUser("Couldn't understand the input, please choose again.");
			run();
			break;
		}
	}
	
	private void closeProgram() {
		ui.printToUser("Shutting down, thank you.");
	}

    /**
     * Gets username from user, checks if it exists and trys to launch UserMenu.
     *  java.lang.Exception if file is not tottaly accessible or doesnot exist.
     */
	private void login() {
		ui.printToUser("Please enter your username:");
		String userName = ui.readFromUser();
		try {
			if(l.getUsernames().contains(userName)){
				ui.printToUser("Welcome "+userName);
				UserMenu um = new UserMenu(l, s, ui, userName);
				um.run();
			}else{
				System.out
                        .println(userName + " does not exist, can't login.");
			}
		} catch (Exception e) {
			System.out
                    .println("couldn't access the file, please try logging in again later");
		}
		
	}

    /**
     * Tries to delete user if he exists.
     * java.io.IOException if file is not accessible / exists.
     */
	private void deleteUser() {
		ui.printToUser("Please enter the username you wish to delete:");
		String user = ui.readFromUser();
		try {
			if(s.deleteUser(user)){
				System.out
                        .println("user "+user+" was deleted successfully");
			}else{
				System.out
                        .println("User "+user+" does not exist.");
			}
		} catch (IOException e) {
			System.out
                    .println("Couldn't delete user, file might be locked by another user or application, please try again");
		}
		
	}

    /**
     * Creates a new user if this does not already exist.
     * java.io.IOException if file is not accessbile.
     */
	private void createNewUser() {
		ui.printToUser("Please enter the new username:");
		String newUser = ui.readFromUser();
		try {
			if(s.addUserName(newUser)){
				ui.printToUser(newUser+" has been added");
			}else{
				ui.printToUser(newUser+" already exists");
			}
		} catch (IOException e) {
			System.out
                    .println("Couldn't add user, file might be locked by another user or application, please try again");
		}
		
	}
}
