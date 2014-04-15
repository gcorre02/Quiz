package main;

import menu.LoginMenu;
import persistence.Loader;
import persistence.Saver;
import userInterface.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
public class LaunchUser {
    public static void main(String[] args) {
        Saver s = new Saver(args[0]);
        Loader l = new Loader(args[0]);
        UserInterface ui = new UserInterface();
        LoginMenu lm = new LoginMenu(l,s,ui);
        lm.run();
    }
}
