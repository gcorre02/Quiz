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
    private static LoaderServer server;
    private static String registryHost;
    private static String serviceName;

    public static void main(String[] args) {
        launch(args[0]);
    }

    private static void launch(String source) {
// 1. If there is no security manager, start one
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
// 2. Create the registry if there is not one
            try{
                LocateRegistry.createRegistry(1099);//TODO set this up better, 1099??
            }catch(RemoteException ex){
                LocateRegistry.getRegistry(1099);
            }
// 3. Create the server object

            server = LoaderServer.getInstance();
            server.setSource(source);//TODO make this input  a variable
            //debug TODO
            System.out.println("this is working at this stage. " + server.toString());
// 4. Register (bind) the server object on the registy.
// The registry may be on a different machine
            registryHost = "//localhost/";
            serviceName = "loader";
            Naming.rebind(registryHost + serviceName, server);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {

            ex.printStackTrace();
        }
        //debug TODO
        System.out.println("Still working at this stage. ");
    }
    public void shutDown(){
        //TODO is the server allways open ? create a serverInstanciator class for server side and a user instantiaor class on client side.
    }
}
