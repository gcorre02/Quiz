package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by user on 16-04-2014.
 */
public class LoaderClient {


    public static void main(String[] args) {
        LoaderClient ec = new LoaderClient();

        //debug
        String callClass = "persistence.Loader";
        String callMethod = "getUsernames";
        try {
            System.out.println(ec.run(callClass, callMethod));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't connect bruv");
        }
        //debug
        
    }

    public <V,T> T run(String inputClass, String inputMethod, V... params)  {

        Remote service = null;
        try {
            service = Naming.lookup("//127.0.0.1:1099/loader");//("//192.168.1.75:1099/loader");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        LoaderService loaderService = (LoaderService) service;
        try {
            T returnableObj = loaderService.doAnythingWithMoreParams(inputClass, inputMethod, params);
            return returnableObj;
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
