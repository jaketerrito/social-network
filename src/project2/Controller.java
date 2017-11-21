package project2;

import java.io.*;

public class Controller {
	Model model;
	View current;
	public Controller(){
		model = new Model("users");
		current = new LoginView(this);
		current.draw();
	}
	
	public boolean login(String username, String password){
		if (model.setUser(username, password)) {
			current = new ProfileView(model.getUser(username),model.getFriendsPosts());
			current.draw();
			return true;
		}
		return false;
	}
	
	public void viewProfile(String username) {
		current = new ProfileView(model.getUser(username),getFriendsPosts());
		current.draw();
	}
}
