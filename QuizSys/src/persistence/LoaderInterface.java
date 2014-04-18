package persistence;

import quizData.Question;
import quizData.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 18-04-2014.
 */
public interface LoaderInterface {
    ArrayList<String> getUsernames() throws IOException;

    Map<String, String[]> getUserQuizzes();

    Quiz getQuizObject(String user, String quizName);

    ArrayList<String> getQuizQuestionsConfig(String owner, String quizName) throws IOException;

    int getQuestionNumber(String questionString, String owner,
                          String quiz) throws IOException;

    Question getQuestionObject(String owner, String quiz,
                               String questionString) throws IOException;
}
