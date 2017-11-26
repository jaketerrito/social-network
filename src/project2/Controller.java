package project2;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	Model model;
	View current;
	String user;
	public Controller(Model model){
		this.model = model;
		current = new LoginView(this);
		current.draw();
	}
	
	public void search(String sub) {
		current.close();
		current = new SearchView(sub, model.getUsers(), model.search(sub), this);
		current.draw();
	}
	
	public boolean login(String username, String password){
		if (model.login(username, password)) {
			current.close();
			user = username;
			home();
			return true;
		}
		return false;
	}
	
	public void logOut() {
		 current.close();
		 model.storeUsers();
		 current = new LogoutView(this);
		 current.draw();
	}
	
	public void viewProfile(String username) {
		current.close();
		User subject = model.getUser(username);
		current = new ProfileView(user,subject, model.getUsers(), model.getFriendsPosts(user),this);
		current.draw();
	}
	
	public void register() {
		current.close();
		current = new RegisterView(this);
		current.draw();
	}
	
	public void home() {
		current.close();
		current = new ProfileView(null,model.getUser(user),model.getUsers(),model.getFriendsPosts(user),this);
		current.draw();
	}
	
	public void refresh() {
		User tempSubject = ((ProfileView)current).getSubject();
		String tempUser = ((ProfileView)current).getViewer();
		current.close();
		current = new ProfileView(tempUser,model.getUser(tempSubject.getUsername()),model.getUsers(),model.getFriendsPosts(user),this);
		current.draw();
	}
	
	public void registerUser(String username,String password,String name, String imageLocation) {
		model.register(username, password, name, imageLocation);
		current.close();
		current = new LoginView(this);
		current.draw();
	}
	
	public String approveUsername(String username) {
		return model.approveUsername(username);
	}
	
	public String approvePassword(String password) {
		return model.approvePassword(password);
	}
	
	public String approveImage(String image) {
		if(image.equals("")) {
			return "Need image";
		}
		//if(NOT A VAlid image)
		return ""; 
	}
	
	public void like(String username, Long time) {
		model.like(user,username, time);
		refresh();
	}
	
	public void changeImage(String imageLocation) {
		model.changeImage(user,imageLocation);
	}
	
	public void changeName(String user, String name) {
		model.changeName(user,name);
	}
	
	public void changeUsername(String user, String username) {
		this.user = username; 
		model.changeUsername(user,username);
		model.storeUsers();
	}
	
	public void changePassword(String user, String password) {
		model.changePassword(user,password);
	}
	
	public void addFriend(String username) {
		model.addFriend(user,username);
		refresh();
	}
	
	public void removeFriend(String username) {
		model.removeFriend(user,username);
		refresh();
	}
	
	public void post(String text) {
		model.post(user,text);
		refresh();
	}
	
	public void settings() {
		current.close();
		current = new SettingsView(model.getUser(user),this);
		current.draw();
	}
	
	public void deactivate() {
		model.deactivate(user);
		logOut();
	}
	
}
