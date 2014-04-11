package menu;

import persistence.Loader;
import persistence.Saver;
import userInterface.UserInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMenu {
	private Loader l;
	private Saver s;
	private UserInterface ui;
	private String user;
}
