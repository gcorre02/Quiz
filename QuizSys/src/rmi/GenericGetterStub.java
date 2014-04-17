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

    public <T,S> T doAnythingWithMoreParams(String... params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        S operator = (S) Class.forName(params[0]).newInstance();

        ArrayList<Class<?>> parameters = new ArrayList<>();
        for(String parameter : params){
            parameters.add(Class.forName(parameter));
        }
        parameters.remove(0);
        parameters.remove(1);

        Method method = operator.getClass().getMethod(params[1],parameters.toArray(new Class<?>[0]));//only works for a method with no params

        return (T) method.invoke(operator);
    }
}
