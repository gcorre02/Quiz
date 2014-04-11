package menu;

import java.io.IOException;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import persistence.Loader;
import persistence.Saver;
import tools.CollectionPrinter;
import userInterface.UserInterface;

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
public class UserMenu {
	private Loader l;
	private Saver s;
	private UserInterface ui;

	public void run(){
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
	
	private void closeProgram() {
		System.out.println("Shutting down, thank you.");
	}
	
	private void login() {
		System.out.println("Please enter your username:");
		// TODO Auto-generated method stub
		
	}
	
	private void deleteUser() {
		System.out.println("Please enter the username you wish to delete:");
		String user = ui.readFromUser();
		try {
			if(s.deleteUser(user)){
				System.out.println("user was deleted successfully");
			}else{
				System.out.println("User does not exist.");
			}
		} catch (IOException e) {
			System.out.println("Couldn't delete user, file might be locked by another user or application, please try again");
		}
		
	}
	
	private void createNewUser() {
		System.out.println("Please enter the new username:");
		String newUser = ui.readFromUser();
		try {
			if(s.addUserName(newUser)){
				System.out.println(newUser+" has been added");
			}else{
				System.out.println(newUser+" already exists");
			}
		} catch (IOException e) {
			System.out.println("Couldn't add user, file might be locked by another user or application, please try again");
		}
		
	}
}
