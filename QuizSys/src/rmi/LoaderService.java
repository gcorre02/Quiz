package rmi; /**
 * Created by user on 16-04-2014.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * An implementation of the echo service.
 * this is the interface for the server class.
 */
public interface LoaderService extends Remote {

    public ArrayList<String> loadUserNames() throws RemoteException;

    <T,S,V> T doAnythingWithMoreParams(String inputClass, String inputMethod, V... params) throws Exception;
}