package persistence;

import quizData.Question;
import quizData.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 18-04-2014.
 */
public interface SaverInterface {
    boolean saveUserNames(ArrayList<String> userNames);

    boolean addUserName(String user) throws IOException;

    boolean deleteUser(String user) throws IOException;

    void deleteFolder(String path);

    /*
* need to improve the json to make it understand the map properly
*/
    boolean saveUserQuizzes(Map<String, String[]> userQuizzes);

    boolean addQuiz(String quizName, String userName, Map<String, String[]> userQuizzes);

    boolean removeQuiz(String quizName, String userName, Map<String, String[]> userQuizzes);

    boolean saveQuiz(Quiz quiz);

    /*
* handles adding a question at every level, updates the object
*/
    boolean addAQuestion(String question, String user, String quiz);

    boolean removeAQuestion(String question, String user, String quiz);

    boolean saveAQuestionObject(Question question);
}
