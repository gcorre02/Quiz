package test.quizDataTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.quizDataTests.test.QuestionTest;
import test.quizDataTests.test.QuizTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        QuestionTest.class,
        QuizTest.class
})
public class TestSuiteQuizData {
}
