package persistence;

import quizData.Question;
import quizData.Quiz;
import rmi.LoaderClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A class that implements the interface for loader and is used by the client to call the real methods in the server.
 *
 *
 * Created by Guilherme Ribeiro on 18-04-2014.
 */
public class LoaderRmiCaller implements LoaderInterface {
    private String source;
    private LoaderClient lc;

    public LoaderRmiCaller(String source){
        this.source = source;

    }
    @Override
    public ArrayList<String> getUsernames() throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod);
    }

    @Override
    public Map<String, String[]> getUserQuizzes() {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod);
    }

    @Override
    public Quiz getQuizObject(String user, String quizName) {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod,user,quizName);
    }

    @Override
    public ArrayList<String> getQuizQuestionsConfig(String owner, String quizName) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod,owner,quizName);
    }

    @Override
    public int getQuestionNumber(String questionString, String owner, String quiz) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod,questionString,owner,quiz);
    }

    @Override
    public Question getQuestionObject(String owner, String quiz, String questionString) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.Loader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod,owner,quiz,questionString);
    }
}
