package rmi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by user on 17-04-2014.
 */
public class GenericGetterStub {
    public <T,S> T doAnything(String... params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        S operator = (S) Class.forName(params[0]).newInstance();
        Method method = operator.getClass().getMethod(params[1]);//only works for a method with no params

        return (T) method.invoke(operator);
    }

    public <T,S,V> T doAnythingWithMoreParams(String inputClass, String inputMethod, V... params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        S operator = (S) Class.forName(inputClass).newInstance();

        ArrayList<Class<?>> parameters = new ArrayList<>();
        for(V parameter : params){
            parameters.add(parameter.getClass());
        }


        Method method = operator.getClass().getMethod(inputMethod,parameters.toArray(new Class<?>[0]));//only works for a method with no params

        return (T) method.invoke(operator, params);
    }
}
