package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by user on 16-04-2014.
 * Lunches the server, still on the server side.
 */
public class LoaderServerLauncher {
    public static void main(String[] args) {
        launch();
    }
    private static void launch() {
// 1. If there is no security manager, start one
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
// 2. Create the registry if there is not one
            LocateRegistry.createRegistry(1099);
// 3. Create the server object
            LoaderServer server = new LoaderServer();
// 4. Register (bind) the server object on the registy.
// The registry may be on a different machine
            String registryHost = "//localhost/";
            String serviceName = "loader";
            Naming.rebind(registryHost + serviceName, server);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    public void shutDown(){
        System.exit(0);
    }
}
