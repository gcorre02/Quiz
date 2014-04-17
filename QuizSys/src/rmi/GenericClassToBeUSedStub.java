package rmi;

import quizData.Quiz;

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

    public Quiz returnsAQuiz(){
        ArrayList<String> questions = new ArrayList<>();
        questions.add("I'm");
        questions.add("Returning");
        questions.add("Stuff");
        questions.add("!!");

        Quiz q = new Quiz("generics","reflectors");
        q.setQuizQuestions(questions);

        return q;
    }

}
