package project2;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.event.*;
/**
 * The controller that communicates between the view and the model, sending input and data.
 * @author jterrito
 *
 */
public class Controller {
	Model model;
	String user;
	JFrame frame;
	
	/**
	 * Creates new controller with given model.
	 * @param model
	 */
	public Controller(Model model){
		this.model = model;
		frame  = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Stores user when application exited.
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        model.storeUsers();
		        System.exit(0);
		    }
		};
		frame.addWindowListener(exitListener);
		frame.setBounds(100, 100, 900, 600);
		frame.setContentPane(new LoginView(this));
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Attempts to login with given username and password.
	 * @param username
	 * @param password
	 * @return Whether login was successful.
	 */
	public boolean login(String username, String password){
		if (model.login(username, password)) {
			user = username;
			home();
			return true;
		}
		return false;
	}
	
	/**
	 * Exit's the program, safely storing the users.
	 */
	public void exit(){
		frame.dispose();
		model.storeUsers();
		System.exit(0);
	}
	
	/**
	 * Log's out, storing the users.
	 */
	public void logOut() {
		 model.storeUsers();
		 frame.setContentPane(new LogoutView(this));
		 frame.pack();
	}
	
	/**
	 * Repacks the frame.
	 */
	public void buttonClick(){
		frame.pack();
	}
	
	/**
	 * Reopens view with updated information from model.
	 */
	public void refresh() {
		User tempSubject = ((ProfileView)frame.getContentPane()).getSubject();
		String tempUser = ((ProfileView)frame.getContentPane()).getViewer();
		frame.setContentPane(new ProfileView(tempUser,model.getUser(tempSubject.getUsername()),model.getUsers(),model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	/**
	 * Opens search panel for given term.
	 * @param term
	 */
	public void search(String term) {
		frame.setContentPane(new SearchView(term, model.search(term), this));
		frame.pack();
	}
	
	/**
	 * Changes view to that of the given user, from information in model.
	 * @param user
	 */
	public void viewProfile(String user) {
		User subject = model.getUser(user);
		frame.setContentPane(new ProfileView(this.user,subject, model.getUsers(), model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	/**
	 * Opens registration panel.
	 */
	public void register() {
		frame.setContentPane(new RegisterView(this));
		frame.pack();
	}
	
	/**
	 * Changes view to user's home, and displays news feed.
	 */
	public void home() {
		frame.setContentPane(new ProfileView(null,model.getUser(user),model.getUsers(),model.getFriendsPosts(user),this));
		frame.pack();
	}
	
	/**
	 * Determines whether or not model approves the username.
	 * @param username
	 * @return
	 */
	public String approveUsername(String username) {
		return model.approveUsername(username);
	}
	
	/**
	 * Determines whether or not the model approves the password.
	 * @param password
	 * @return
	 */
	public String approvePassword(String password) {
		return model.approvePassword(password);
	}
	
	/**
	 * Determines whether or not the image is valid.
	 * @param image
	 * @return
	 */
	public String approveImage(String image) {
		if(image.equals("")) {
			return "Need image";
		}
		//Not implemented
		//return model.approveImage(image);
		return "";
	}
	
	/**
	 * Sends new user information for model to add to database.
	 * @param username
	 * @param password
	 * @param name
	 * @param imageLocation
	 */
	public void registerUser(String username,String password,String name, String imageLocation) {
		model.register(username, password, name, imageLocation);
		frame.setContentPane(new LoginView(this));
		frame.pack();
	}
	
	public void like(String otheruser, Long time) {
		model.like(user, otheruser, time);
		refresh();
	}
	
	public void changeImage(String imageLocation) {
		model.changeImage(user,imageLocation);
	}
	
	public void changeName(String currentUser, String name) {
		model.changeName(currentUser,name);
	}
	
	public void changeUsername(String currentUser, String username) {
		this.user = username; 
		model.changeUsername(currentUser,username);
	}
	
	public void changePassword(String currentUser, String password) {
		model.changePassword(currentUser,password);
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
