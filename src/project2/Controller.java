package project2;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
public class Controller {
	Model model;
	String user;
	JFrame frame;
	public Controller(Model model){
		frame  = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 600);
		this.model = model;
		frame.setContentPane(new LoginView(this));
		frame.pack();
		frame.setVisible(true);
	}
	
	public void exit(){
		frame.dispose();
		model.storeUsers();
	}
	
	public void buttonClick(){
		frame.pack();
	}
	
	public void search(String sub) {
		frame.setContentPane(new SearchView(sub, model.getUsers(), model.search(sub), this));
		frame.pack();
	}
	
	public boolean login(String username, String password){
		if (model.login(username, password)) {
			user = username;
			home();
			return true;
		}
		return false;
	}
	
	public void logOut() {
		 model.storeUsers();
		 frame.setContentPane(new LogoutView(this));
		 frame.pack();
	}
	
	public void viewProfile(String username) {
		User subject = model.getUser(username);
		frame.setContentPane(new ProfileView(user,subject, model.getUsers(), model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	public void register() {
		frame.setContentPane(new RegisterView(this));
		frame.pack();
	}
	
	public void home() {
		frame.setContentPane(new ProfileView(null,model.getUser(user),model.getUsers(),model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	public void refresh() {
		User tempSubject = ((ProfileView)frame.getContentPane()).getSubject();
		String tempUser = ((ProfileView)frame.getContentPane()).getViewer();
		frame.setContentPane(new ProfileView(tempUser,model.getUser(tempSubject.getUsername()),model.getUsers(),model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	public void registerUser(String username,String password,String name, String imageLocation) {
		model.register(username, password, name, imageLocation);
		frame.setContentPane(new LoginView(this));
		frame.pack();
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
		frame.setContentPane(new SettingsView(model.getUser(user),this));
		frame.pack();
	}
	
	public void deactivate() {
		model.deactivate(user);
		logOut();
	}
	
}
