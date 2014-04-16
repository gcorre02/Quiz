package rmi; /**
 * Created by user on 16-04-2014.
 */

import tools.CollectionPrinter;

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
    public ArrayList<String> loadUserNames() {
        ArrayList<String> s = new ArrayList<>();
        s.add("test");
        s.add("1");
        s.add("2");
        s.add("3");
    // This println is not necessary, but helps verifying whether
    // the server has received the call or not on the remote machine
        System.out.println("Replied to some client with usernames ’" + CollectionPrinter.collectionPrinter('0', s) + "’");
        return s;
    }
}
