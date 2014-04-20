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
//
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
//Locate or create registry
           try{
                LocateRegistry.createRegistry(1099);//TODO set this up better, 1099??
            }catch(RemoteException ex){
                LocateRegistry.getRegistry(1099);
            }

            server = LoaderServer.getInstance();
            server.setSource(source);//TODO make this input  a variable

            System.out.println("this is working at this stage. " + server.toString());

//bind the server object
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
    }
}
