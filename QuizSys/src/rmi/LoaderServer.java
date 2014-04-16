package rmi; /**
 * Created by user on 16-04-2014.
 */

import persistence.Loader;
import tools.CollectionPrinter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Implements Echo Service
 * Impl of the Server.
 */
public class LoaderServer extends UnicastRemoteObject implements LoaderService {
    public LoaderServer() throws RemoteException {
        // nothing to initialise for this server
    }

    @Override
    public ArrayList<String> loadUserNames(String source) {
        Loader l = new Loader(source);
        ArrayList<String> s = new ArrayList<>();
        s.add("empty, LoaderServer failed");
        try {
            s = l.getUsernames();
        } catch (IOException e) {
            System.out.println("Can't load usernames.");
        }
        //debug

    //\debug
    // This println is not necessary, but helps verifying whether
    // the server has received the call or not on the remote machine
        System.out.println("Replied to some client with usernames ’" + CollectionPrinter.collectionPrinter('0', s) + "’");
        return s;
    }
}
