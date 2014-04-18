package quizData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor //for deserialization
@AllArgsConstructor
public class Question implements Serializable{
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
