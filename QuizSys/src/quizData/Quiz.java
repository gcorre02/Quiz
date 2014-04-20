package quizData;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Data
public class Quiz implements Serializable {
	private String owner;
	private ArrayList<String> quizQuestions;
	private String quizName;
    private LinkedHashMap<Double, String> highestScore;

    public Quiz(){
        //single instance creator
        quizName = "Stubbed";
    }

	public Quiz(String name, String owner){
		quizQuestions = new ArrayList<>();
		quizName = name;
		this.owner = owner;
        highestScore=new LinkedHashMap<>();
        highestScore.put(0.0, "Nobody");
	}

    //TODO need to have a field that is set if quiz can be played or not. corresponding checks also in the PlayerMenus

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
