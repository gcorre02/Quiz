package quizData;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
	private String questionString;
	private ArrayList<String> answers;
	private int rightAnswer;
	private String owner;
	private String quiz;
	
	public Question(String questionString) {
		this.questionString = questionString;
		answers = new ArrayList<>();
	}
	
	public void addAnswer(String answerString){
		answers.add(answerString);
	}
	
	public void removeAnswer(int i) {
		answers.remove(i);		
	}

	public String getAnswer(int i) {
		return answers.get(i);
	}
	
	@Override
	public String toString(){
		return questionString;
	}

}
