package quizData;

import java.util.ArrayList;

import tools.CollectionPrinter;
import lombok.Data;

@Data
public class Quiz {
	private String owner;
	private ArrayList<Question> quizQuestions;
	private String quizName;
	
	public Quiz(String name){
		quizQuestions = new ArrayList<>();
		quizName = name;
	}
	
	public void addQuestion(String questionString){
		Question newQuestion = new Question(questionString);
		quizQuestions.add(newQuestion);
	}
	
	@Override
	public String toString(){
		return  quizName;
	}

	public void removeQuestion(int i) {
		quizQuestions.remove(i);		
	}

	public Question getQuestion(int i) {
		return quizQuestions.get(i);
	}
	
	

}
