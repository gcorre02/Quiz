package persistence;

import com.google.gson.Gson;
import lombok.Data;
import quizData.Question;
import quizData.Quiz;
import tools.CollectionTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * TODO <>no support for changing question numbers at the moment.
 * 
 * source defines the folder where all the quiz system files are.
 * TODO<concurrency> some conflict checking must go in here.
 * @author Guilherme
 *
 */
@Data
public class Saver implements SaverInterface {
	String source;
	File file;
	public Saver(String path){
		source = path;
		createFolder(path);
		createUserNames(path);
		createUserQuizzes(path);
	}

	private void createUserQuizzes(String path) {
		File f = new File(path + File.separator + "UserQuizzes.txt");
		if(!f.exists()){
			Map<String,String[]> userNames = new HashMap<>();
			userNames.put("Admin",new String[0]);
			saveUserQuizzes(userNames);
		}
	}

	private void createUserNames(String path) {
		File f = new File(path + File.separator + "UserNames.txt");
		if(!f.exists()){
			ArrayList<String> userNames = new ArrayList<>();
			userNames.add("Admin");
			saveUserNames(userNames);
		}
	}

	private void createFolder(String path) {
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdir();
		}
	}
	/**
	 * 
	 * add User names should be called usually, saveUserNames has the danger of overwriting existing users
	 * TODO <overwriting> make it private !!!???
	 * @param userNames
	 * @return
	 */
	@Override
    public boolean saveUserNames(ArrayList<String> userNames){
		Gson gson = new Gson();
		file = new File(source+File.separator+"UserNames.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(userNames.toArray()));
		w.close();
		createUserFolders(userNames);
		return true;
	}

	@Override
    public boolean addUserName(String user) throws IOException{
		Loader l = new Loader(source);
		ArrayList<String> existingUserNameStrings = l.getUsernames();
		if(existingUserNameStrings.contains(user)){
			addUserToUserQuizzes(user);
			return false;
		} else {
			existingUserNameStrings.add(user);
			saveUserNames(existingUserNameStrings);
			addUserToUserQuizzes(user);
			return true;
		}
	}

	private void addUserToUserQuizzes(String user) {
		Loader l = new Loader(source);
		Map<String, String[]> userQuizzes = l.getUserQuizzes();
		if(!userQuizzes.containsKey(user)){
			userQuizzes.put(user, new String[0]);
		}
		//TODO error checking:
		saveUserQuizzes(userQuizzes);
	}

	/*
	 * creates folders for each user, if these do not exist already.
	 *
	 */
	private void createUserFolders(ArrayList<String> users){
		for(String user : users){
			createFolder(source+File.separator+user);
		}
	}
	@Override
    public boolean deleteUser(String user) throws IOException{
		Loader l = new Loader(source);
		if(deleteUserFromUserQuizzes(user)){
			System.out.println("user successfully removed from userQuizzes");
		}else{
			System.out.println("Couldn't remove user from user quizzes. please try again later.");
			return false;
		}
			
		ArrayList<String> users = l.getUsernames();
		if(!users.contains(user)){
			return false;
		} else {
			users.remove(user);
			saveUserNames(users);
			String path =source + File.separator + user; 
			deleteFolder(path);
			File f = new File(path);
			if(f.exists()){
				return false;
			}
			return true;
		}
	}

	private boolean deleteUserFromUserQuizzes(String user) {
		Loader l = new Loader(source);
		Map<String, String[]> userQuizzes = l.getUserQuizzes();
		if(userQuizzes.containsKey(user)){
			userQuizzes.remove(user);
			if(saveUserQuizzes(userQuizzes))
					return true;
		}
		return false;
	}

	@Override
    public void deleteFolder(String path) {
		File file = new File(path);
		if(file.list()!=null){
			int i = file.list().length;
			for(int t = 0; t<i; t++){
				String s = file.listFiles()[0].toPath().toString();
				deleteFolder(s);
			}
		}
		file.delete();
	}
	/*
	 * need to improve the json to make it understand the map propperly
	 */
	@Override
    public boolean saveUserQuizzes(Map<String, String[]> userQuizzes){
		if(!writeUserQuizzesJson(userQuizzes)){
			return false;
		}
		try {
			createQuizFiles(userQuizzes);
		} catch (IOException e) {
			System.out.println("couldn't set up all the quizz files");
			return false;
		}
		return true;
	}

	private boolean writeUserQuizzesJson(Map<String, String[]> userQuizzes) {
		Gson gson = new Gson();
		file = new File(source+File.separator+"UserQuizzes.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(userQuizzes));
		w.close();
		return true;
	}
	private void createQuizFiles(Map<String, String[]> userQuizzes) throws IOException {
		Loader l = new Loader(source);
		for(String user : userQuizzes.keySet()){
			if(!l.getUsernames().contains(user)){
				addUserName(user);
			}
			generateQuizFiles(user, userQuizzes.get(user));
		}
	}
	/*
	 * needs to check if the files already exist.
	 */
	private void generateQuizFiles(String user, String[] strings) throws IOException {
		for(String quizname: strings){
			createFolder(source+File.separator+user+File.separator+quizname);
			File quizFile = new File(source+File.separator+user+File.separator+quizname+File.separator+quizname+".txt");
			if(!quizFile.exists()){
				quizFile.createNewFile();
			}
		}
	}

	@Override
    public boolean addQuiz(String quizName, String userName, Map<String, String[]> userQuizzes){
		//error checking
		if(!userQuizzes.keySet().contains(userName)){
			System.out.println("User " + userName + " does not exist.");
			return false;
		}

		if(CollectionTools.arrayContains(quizName, userQuizzes.get(userName))){
			System.out.println("A quiz with the name " + quizName + " already exists.");
			return true;
		}
		//exec
		
		userQuizzes.put(userName, CollectionTools.addElementToArray(quizName, userQuizzes.get(userName)));
		//debug
		//System.out.println(CollectionTools.printMap(userQuizzes));
		//end debug
		if(saveUserQuizzes(userQuizzes))
			return true;
		else
			return false;
	}

	@Override
    public boolean removeQuiz(String quizName, String userName, Map<String, String[]> userQuizzes){
		//error checking
		if(!userQuizzes.keySet().contains(userName)){
			System.out.println("User " + userName + " does not exist.");
			return false;
		}

		if(!CollectionTools.arrayContains(quizName, userQuizzes.get(userName))){
			System.out.println("A quiz with the name " + quizName + " doesnt exist");
			return true;
		}
		
		//exec
		userQuizzes.put(userName, CollectionTools.removeElementFromArray(quizName, userQuizzes.get(userName)));
		//deleteQuizFile
		String path = source + File.separator + userName + File.separator + quizName;
		deleteFolder(path);
		file = new File(path);
		//persist
		if(saveUserQuizzes(userQuizzes)
				|| !file.exists())
			return true;
		else
			return false;
	}
	/**
	 * each quiz has a config file, which has the name of the quiz and stores the name of the quiz object file, and associated questions.
	 * returns false if config file doesnt exist, which means it's not in the structure, which means that addquiz hasnt been called first.
	 * @return
	 */
	@Override
    public boolean saveQuiz(Quiz quiz){
		File f = new File(source+File.separator + quiz.getOwner()+File.separator+quiz.getQuizName());
		if(!f.exists()) {
            System.out.println(f.getAbsolutePath() + "doesn't exist, can't save quiz file.");
            return false;
        }
		if(
				!saveQuizConfig(quiz.getOwner(),quiz.getQuizName(),quiz.getQuizQuestions())
				||!saveQuizObject(quiz)
				||!generateQuestionFiles(quiz))
			return false;
		return true;
	}


	private boolean saveQuizConfig(String owner, String quizName, ArrayList<String> questions) {
		Gson gson = new Gson();
		file = new File(source+File.separator+owner+File.separator+quizName+File.separator+quizName+".txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(questions));
		w.close();
		return true;
	}
	
	private boolean saveQuizObject(Quiz quiz) {
		Gson gson = new Gson();
		String quizName = quiz.getQuizName();
		String owner = quiz.getOwner();
		file = new File(source+File.separator+owner+File.separator+quizName+File.separator+quizName+"Object.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(quiz));
		w.close();
		return true;
	}
	private boolean generateQuestionFiles(Quiz quiz) {
        //TODO replace integer name of new file with actual question name, removing ? mark.
		for(int i = 0; i < quiz.getQuizQuestions().size(); i++){
			String path = source + File.separator + quiz.getOwner() + File.separator + quiz.getQuizName() + File.separator +i+ ".txt";
			File f = new File(path);
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					System.out.println("couldn't create the new question file");
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * handles adding a question at every level, updates the object
	 */
	@Override
    public boolean addAQuestion(String question, String user, String quiz){
		Loader l = new Loader(source);
		try {
			if(l.getQuizQuestionsConfig(user, quiz).contains(question))
				return true;
		} catch (IOException e) {
			System.out.println("couldnt load the quizz config file");
			return false;
		}
		Quiz q = l.getQuizObject(user, quiz);
		q.addQuestion(question);
		if(saveQuiz(q))
			return true;
		return false;
	}
	@Override
    public boolean removeAQuestion(String question, String user, String quiz){
		Loader l = new Loader(source);
		ArrayList<String> quizConfig;
		try {
			quizConfig = l.getQuizQuestionsConfig(user, quiz);
			if(!quizConfig.contains(question))
				return true;
		} catch (IOException e) {
			System.out.println("couldnt load the quiz config file");
			return false;
		}
		Quiz q = l.getQuizObject(user, quiz);
		int questionId = quizConfig.indexOf(question);
		q.removeQuestion(questionId );
		String path = source + File.separator + user + File.separator + quiz + File.separator + questionId+".txt";
		deleteFolder(path);
		if(saveQuiz(q))
			return true;
		return false;
	}
	
	@Override
    public boolean saveAQuestionObject(Question question){
        Loader l = new Loader(source);
        int qNumber;
		try {
            qNumber = l.getQuestionNumber(question.getQuestionString(), question.getOwner(), question.getQuiz());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		String path = source + File.separator + question.getOwner() + File.separator + question.getQuiz() + File.separator + qNumber+".txt";
		try{
			saveQuestionObjectJson(question, path);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private void saveQuestionObjectJson(Question question, String path) throws FileNotFoundException {
		Gson gson = new Gson();
		file = new File(path);
		PrintWriter w;
		w = new PrintWriter(file);
		w.println(gson.toJson(question));
		w.close();
	}
}
