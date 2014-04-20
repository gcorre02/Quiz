package main.LocalUserLaunchers;

import menu.LoginMenu;
import persistence.Loader;
import persistence.LoaderInterface;
import persistence.Saver;
import persistence.SaverInterface;
import tools.UserInterface;

/**
 * Launches the local User System based on the source file set up.
 *
 * Created by Admin on 15/04/2014.
 */
public class LaunchUserLocalVersion {

    /**
     * Launches the UserSystem
     *
     * @param args the [0] position indicates the path where the file structure lies.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        launch(args[0], ui);
    }

    /**
     * separated method for inputting a UserInterface mock.
     * @param source The path to the folder where the file structure is.
     * @param ui a potential mock of user interface.
     */
    public static void launch(String source, UserInterface ui) {
        SaverInterface s = new Saver(source);
        LoaderInterface l = new Loader(source);
        LoginMenu lm = new LoginMenu(l,s,ui);
        lm.run();
    }
}
