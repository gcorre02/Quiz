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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * TODO <>no support for changing question numbers at the moment.
 * TODO<concurrency> some conflict checking must go in here.
 *
 * Server side real methods for handling user data.
 *
 * @author Guilherme
 *
 */
@Data
public class Saver implements SaverInterface {
	String source;
	File file;


    /**
     * Makes sure the file system structure is corrent and generates it if not.
     * @param path defines the folder where all the quiz system files are.
     */
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
	 * add User names should be called usually, saveUserNames has the danger of overwriting existing users.
     * Generates the file for storing the usernames registry.
	 * @param userNames the array with all the usernames registered in the system.
	 * @return true if operation works.
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

    /**
     * registers a new user to the structure. created basic stub file structure for the user.
     * @param user the name of the user to be registered.
     * @return true if method's behaviour works.
     * @throws IOException if file structure cannot be accessed propperly.
     */
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

    /**
     * removes a user completely for the system.
     * @param user name of the user to remove.
     * @return true if removal is successful
     * @throws IOException if file structure is not accessed properly
     */
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

    /**
     * deletes folder and all children.
     * @param path to the folder to remove.
     */
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
	 * persists the userQuizzes reference structure.
	 * UserQuizzes maps each user to their quizzes.
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
	private void generateQuizFiles(String user, String[] quizNames) throws IOException {
		for(String quizname: quizNames){
			createFolder(source+File.separator+user+File.separator+quizname);
			File quizFile = new File(source+File.separator+user+File.separator+quizname+File.separator+quizname+".txt");
			if(!quizFile.exists()){
                Quiz q = new Quiz(quizname,user);
                saveQuiz(q);
			}
		}

	}

    /**
     * adds a quiz object to the structure
     * @param quizName of the quiz
     * @param userName of the user
     * @param userQuizzes the quiz referencing structure
     * @return true if methods behaves as expected
     */
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

		if(saveUserQuizzes(userQuizzes))
			return true;
		else
			return false;
	}

    /**
     * Version of addQuizz that takes a LinkedHashMap as parameter for whence it is called over the network.
     *
     * @param quizName
     * @param userName
     * @param userQuizzes
     * @return true if the structure works.
     */
    @Override
    public boolean addQuiz(String quizName, String userName, LinkedHashMap<String, String[]> userQuizzes){
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

    /**
     * Overload of removeQuiz with LinkedHashMap as a parameter for handling requests over the network.
     * @param quizName
     * @param userName
     * @param userQuizzes
     * @return true if the removal is successful.
     */
    @Override
    public boolean removeQuiz(String quizName, String userName, LinkedHashMap<String, String[]> userQuizzes) {
        return removeQuiz(quizName,userName,(Map)userQuizzes);
    }

    /**
     * removes and cleans up all files relevant to  the referenced quiz in the system.
     * @param quizName
     * @param userName
     * @param userQuizzes
     * @return false if quiz already does not exist or accessing the structure is not possible.
     */
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
	 * @return true if saving the object is successful.
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

    /**
     * handles adding a question at every level, updates the object
     * @param question String for the question itself
     * @param user owner of the quiz
     * @param quiz name
     * @return true if persisting Question object is successful
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

    /**
     * removes referenced question
     * @param question String for the question itself
     * @param user name of the user
     * @param quiz name that owns the question
     * @return true if operation is successful
     */
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

    /**
     * persists passed question object
     *
     * @param question object to persist
     * @return true if persisting operation is successful
     */
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
