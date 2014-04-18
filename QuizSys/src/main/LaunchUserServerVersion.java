package main;

import menu.LoginMenu;
import persistence.*;
import userInterface.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
public class LaunchUserServerVersion {
    private static LoaderInterface l;
    private static SaverInterface s;
    public static void main(String[] args) {
        s = new SaverRmiCaller(args[0]);
        l = new LoaderRmiCaller(args[0]);
        UserInterface ui = new UserInterface();
        LoginMenu lm = new LoginMenu(l,s,ui);
        lm.run();
    }
}
