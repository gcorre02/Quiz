package main.LocalUserLaunchers;

import persistence.*;
import player.PlayerLoginMenu;
import tools.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
public class LaunchPlayerServerVersion {
    private static PlayerLoaderInterface pl;
    private static PlayerSaverInterface ps;

    public static void main(String[] args) {
        ps = new PlayerSaverRmiCaller(args[0]);
        pl = new PlayerLoaderRmiCaller(args[0]);
        UserInterface ui = new UserInterface();
        PlayerLoginMenu plm = new PlayerLoginMenu(pl,ps,ui);
        plm.run();
    }
}
