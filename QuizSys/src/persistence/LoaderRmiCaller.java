package persistence;

import quizData.Question;
import quizData.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 18-04-2014.
 */
public class LoaderRmiCaller implements LoaderInterface {
    @Override
    public ArrayList<String> getUsernames() throws IOException {
        return null;
    }

    @Override
    public Map<String, String[]> getUserQuizzes() {
        return null;
    }

    @Override
    public Quiz getQuizObject(String user, String quizName) {
        return null;
    }

    @Override
    public ArrayList<String> getQuizQuestionsConfig(String owner, String quizName) throws IOException {
        return null;
    }

    @Override
    public int getQuestionNumber(String questionString, String owner, String quiz) throws IOException {
        return 0;
    }

    @Override
    public Question getQuestionObject(String owner, String quiz, String questionString) throws IOException {
        return null;
    }
}
