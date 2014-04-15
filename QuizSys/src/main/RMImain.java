package main;

/**
 * Created by Admin on 15/04/2014.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMImain extends Remote {
    void executeBasicMain() throws RemoteException;
}
