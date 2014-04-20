package persistence;


import quizData.Question;
import quizData.Quiz;
import rmi.LoaderClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * client side Saver to call real methods over the network.
 *
 * Created by user on 18-04-2014.
 */
public class SaverRmiCaller implements SaverInterface {
    private String source;
    private LoaderClient lc;

    public SaverRmiCaller(String source){
        this.source = source;
    }

    @Override
    public boolean saveUserNames(ArrayList<String> userNames) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, userNames);
    }

    @Override
    public boolean addUserName(String user) throws IOException {

        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, user);

    }

    @Override
    public boolean deleteUser(String user) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, user);
    }

    @Override
    public void deleteFolder(String path) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        lc.run(callClass,callMethod, path);
    }

    @Override
    public boolean saveUserQuizzes(Map<String, String[]> userQuizzes) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, userQuizzes);
    }

    @Override
    public boolean addQuiz(String quizName, String userName, Map<String, String[]> userQuizzes) {
        LinkedHashMap<String, String[]> out = (LinkedHashMap<String, String[]>) userQuizzes;
        return addQuiz(quizName,userName,out);
    }

    @Override
    public boolean addQuiz(String quizName, String userName, LinkedHashMap<String, String[]> userQuizzes) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, quizName,userName,userQuizzes);
    }

    @Override
    public boolean removeQuiz(String quizName, String userName, LinkedHashMap<String, String[]> userQuizzes) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, quizName, userName, userQuizzes);
    }
    @Override
    public boolean removeQuiz(String quizName, String userName, Map<String, String[]> userQuizzes) {
        LinkedHashMap<String, String[]> out = (LinkedHashMap<String, String[]>) userQuizzes;
        return removeQuiz(quizName,userName,out);
    }

    @Override
    public boolean saveQuiz(Quiz quiz) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, quiz);
    }

    @Override
    public boolean addAQuestion(String question, String user, String quiz) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, question, user, quiz);
    }

    @Override
    public boolean removeAQuestion(String question, String user, String quiz) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, question, user, quiz);
    }

    @Override
    public boolean saveAQuestionObject(Question question) {
        lc = new LoaderClient();
        String callClass = "persistence.Saver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, question);
    }
}
