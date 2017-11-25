package project2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

//the model is shared and certain functions need to be synchronized
//make a singleton

public class Model {
	private static Model modelInstance;
	
	private String usersDir;
	private HashMap<String,User> userList;
	
	private Model(String usersDir) {
		this.usersDir = usersDir;
		readUsers();
	}
	
	public static Model getInstance(String usersDir) {
		if(modelInstance == null) {
			modelInstance = new Model(usersDir);
		}
		return modelInstance;
	}
	

	public void readUsers() {
		User temp;
		userList = new HashMap<String,User>();
		for(File file: new File(usersDir).listFiles()){
			temp = new User(file);
	        userList.put(temp.getUsername(), temp);
		}
	}
	
	public void storeUsers() {
		for(String username:userList.keySet()) {
			userList.get(username).toFile(usersDir);
		}
	}
	
	public HashMap<String,User> getUsers() {
		return userList;
	}
	
	
	public void post(String currentUser, String post){
		User user = getUser(currentUser);
		user.post(post);
	}
	
	public ArrayList<Post> getFriendsPosts(String currentUsername){
		ArrayList<Post> posts = new ArrayList<Post>();
		for(String friendname:userList.get(currentUsername).getFriends()) {
			if(userList.get(friendname) == null) {
				System.out.println("Error: invalid friend");
				continue;
			}
			for(Post post:userList.get(friendname).getPosts()) {
				posts.add(post);
			}
		}
		return posts;
	}
	
	public User getUser(String user) {
		return userList.get(user);
	}
	
	public boolean login(String username, String password) {
		User temp = getUser(username);
		if(temp == null) {
			return false;
		}
		if(temp.isPassword(password)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String> search(String sub){
		ArrayList<String> matches = new ArrayList<String>();
		for(String username: userList.keySet()) {
			if((userList.get(username)).getName().toLowerCase().contains(sub.toLowerCase())) {
					matches.add(username);
			}
		}
		return matches;
	}
	
	public void register(String username, String password, String name, String imageLocation){
		if(imageLocation.equals("")) {
			imageLocation = "defaultPic.png";
		}
		userList.put(username,new User(username,password,name,imageLocation));
		userList.get(username).toFile(usersDir);
	}
	
	public void addFriend(String currentUser, String friendName) {
		User user = getUser(currentUser);
		user.addFriend(friendName);
		user = getUser(friendName);
		user.addFriend(currentUser);
	}
	
	public void removeFriend(String currentUser, String friendName) {
		User user = getUser(currentUser);
		user.removeFriend(friendName);
		user = getUser(friendName);
		user.removeFriend(currentUser);
	}
	
	public void like(String currentUser, String username,Long time) {
		User user = getUser(username);
		user.like(time,currentUser);
	}
	
	public void deactivate(String currentUser) {
		try {
		    Files.delete(Paths.get(usersDir + "/" + currentUser + ".txt"));
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		//delete any reference to this user in likes or friends
		for(String username:userList.keySet()) {
			userList.get(username).removeFriend(currentUser);
		}
		userList.remove(currentUser);
	}
	
	public void changeUsername(String user,String username) {
		try {
		    Files.delete(Paths.get(usersDir + "/" + user + ".txt"));
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		for(String name:userList.keySet()) {
			userList.get(name).changeUsername(user,username);
		}
		userList.put(username, userList.get(user));
		userList.remove(user);
	}
	
	public String approveUsername(String username) {
		if(username.equals("")) {
			return "Need username";
		}
		if(username.contains(" ") || username.contains("\n") || username.contains(",")) {
			return "No special characters";
		}
		if(getUser(username) != null) {
			return "Username taken";
		}
		return "";
	}
	
	public String approvePassword(String password) {
		if(password.equals("")) {
			return "Need password";
		}
		if(password.contains(" ") || password.contains("\n") || password.contains(",")) {
			return "No special characters";
		}
		return "";
	}
	
	public void changeImage(String currentUser, String imageLocation) {
		User user = getUser(currentUser);
		user.setImage(imageLocation);
	}
	
	public void changePassword(String user,String password) {
		userList.get(user).setPassword(password);
	}
	
	public void changeName(String user,String name) {
		userList.get(user).setName(name);
		
	}
}
