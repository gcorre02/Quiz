package main;

import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Admin on 15/04/2014.
 */
public class RmiMainExec implements RMImain{
    @Override
    public void executeBasicMain() throws RemoteException {
        final String[] FOLDER = {"RMITry"};
        UserInterface ui = new UserInterface();
        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionPrinter.collectionPrinter('S', CollectionPrinter.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayer.main(FOLDER);
        else if(choice == 'B')
            LaunchUser.main(FOLDER);
        else
            System.out.println("Couldn't understand input. Bye");
    }

    public static void main(String[] args) {
        RMImain rme = new RmiMainExec();
        try {
            RMImain stub = (RMImain) UnicastRemoteObject.exportObject(rme, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

