package main;

import menu.LoginMenu;
import persistence.Loader;
import persistence.LoaderInterface;
import persistence.Saver;
import persistence.SaverInterface;
import userInterface.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
public class LaunchUserLocalVersion {
    private static LoaderInterface l;
    private static SaverInterface s;
    public static void main(String[] args) {
        s = new Saver(args[0]);
        l = new Loader(args[0]);
        UserInterface ui = new UserInterface();
        LoginMenu lm = new LoginMenu(l,s,ui);
        lm.run();
    }
}
