package rmi;

import java.util.ArrayList;

/**
 * Created by user on 17-04-2014.
 */
public class GenericClassToBeUSedStub {
    public String saySomething(){
        return "Hello!";
    }
    public ArrayList<String> sayABunchOfStuff(){
        ArrayList<String> returnable = new ArrayList<>();
        returnable.add("I'm");
        returnable.add("Returning");
        returnable.add("Stuff");
        returnable.add("!!");
        return returnable;
    }
}
