package quizData;

import java.util.ArrayList;

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

	public String orderedQuestions() {
		String result = "";
		int i = 0;
		for(Question q : quizQuestions){
			result += i +" -> "+q.getQuestionString() + "\n";
			i++;
		}
		return result;
	}
	

}
