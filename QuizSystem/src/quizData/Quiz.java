package quizData;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Quiz {
	private String owner;
	private ArrayList<Question> quizQuestions;
	
	public void addQuestion(String questionString){
		Question newQuestion = new Question(questionString);
	}
}
