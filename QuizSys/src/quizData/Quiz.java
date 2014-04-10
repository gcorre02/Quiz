package quizData;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Quiz {
	private String owner;
	private ArrayList<String> quizQuestions;
	private String quizName;
	
	public Quiz(String name, String owner){
		quizQuestions = new ArrayList<>();
		quizName = name;
		this.owner = owner;
	}

	public void addQuestion(String questionString){
		quizQuestions.add(questionString);
	}
	
	public void removeQuestion(int i) {
		quizQuestions.remove(i);		
	}

	public String getQuestion(int i) {
		return quizQuestions.get(i);
	}
	
	@Override
	public String toString(){
		return  quizName;
	}
	

}
