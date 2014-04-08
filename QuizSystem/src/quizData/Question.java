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
