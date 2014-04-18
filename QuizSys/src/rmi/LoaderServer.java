package rmi; /**
 * Created by user on 16-04-2014.
 */

import persistence.Loader;
import tools.CollectionPrinter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Implements Echo Service
 * Impl of the Server.
 *
 * Server knows where it's files are. source is a local field. PROBABLY A SINGLETON
 */

public class LoaderServer extends UnicastRemoteObject implements LoaderService {
    private LoaderServer() throws RemoteException {
        // nothing to initialise for this server
    }
    private static String source = "testFiles";//TODO
    private static boolean instanciated = false;
    private static LoaderServer instance;

    protected void setSource(String source){
        this.source = source;
    }

    public static LoaderServer getInstance() throws RemoteException {
        if(!instanciated) {
            instance = new LoaderServer();
        }
        return instance;

    }

    @Override
    public ArrayList<String> loadUserNames() throws RemoteException{
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

    @Override //TODO, quizData objects are not serializable yet.
    public <T,S,V> T doAnythingWithMoreParams(String inputClass, String inputMethod, V... params)  {
        S operator = null;
        try {
            operator = (S) Class.forName(inputClass).getDeclaredConstructor(String.class).newInstance(source);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Class<?>> parameters = new ArrayList<>();
        for(V parameter : params){
            parameters.add(parameter.getClass());
        }


        Method method = null;//only works for a method with no params
        try {
            method = operator.getClass().getMethod(inputMethod,parameters.toArray(new Class<?>[0]));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        T returnableObj = null;
        try {
            returnableObj = (T) method.invoke(operator, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("returning object : " + returnableObj);//TODO make this input  a variable

        return returnableObj;
    }
}
