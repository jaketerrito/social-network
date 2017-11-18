package project2;

public class Controller {
	Model model;
	
	public Controller(){
		model = new Model();
	}
	
	public boolean login(String username, String password){
		return model.setUser(username,password);
	}
}
