package quizData;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Question {
	private String questionString;
	private ArrayList<String> answers;
	private String rightAnswer;
	
	public Question(String questionString) {
		this.questionString = questionString;
	}

}
