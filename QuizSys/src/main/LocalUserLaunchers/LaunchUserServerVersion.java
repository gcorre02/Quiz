package main.LocalUserLaunchers;

import menu.LoginMenu;
import persistence.*;
import tools.UserInterface;

/**
 * Launches the UserSystem Server version
 *
 * Created by Guilherme on 15/04/2014.
 */
public class LaunchUserServerVersion {

    /**
     * The User System launcher for the server version.
     * @param args [0] position the path to the file structure.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        launch(args[0], ui);
    }

    /**
     * Alternative launching method for inputing a UserInterface obj
     * @param source the path to the Quiz File Structure
     * @param ui the inputted UserInterface obj
     */
    public static void launch(String source, UserInterface ui) {
        SaverInterface s = new SaverRmiCaller(source);
        LoaderInterface l = new LoaderRmiCaller(source);
        LoginMenu lm = new LoginMenu(l,s,ui);
        lm.run();
    }
}
