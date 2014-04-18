package rmi;

import userInterface.UserInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by user on 16-04-2014.
 */
public class LoaderClient {
    private static UserInterface ui;

    public LoaderClient(UserInterface uiInput){
        ui = uiInput;
    }
/*
    public static void main(String[] args) {
        LoaderClient ec = new LoaderClient(ui);
        ec.run();
    }
*/
    public <V> void run(String inputClass, String inputMethod, V... params) throws Exception {
        if(ui == null)
            ui = new UserInterface();
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
            System.out.println(loaderService.doAnythingWithMoreParams(inputClass,inputMethod, params));//TODO make this input  a variable
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
