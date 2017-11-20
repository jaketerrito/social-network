package project2;

import java.io.*;
import java.util.*;

//shouldn't be instances of these lists because other running versions could change the files at anytime and make these invalid

public class Model {
	private String usersDir;
	private String username;
	
	public Model(String usersDir) {
		this.usersDir = usersDir;
	}
	
	//makes list of users from database
	//should not read if toFile is happening
	public HashMap<String,User> readUsers() {
		User temp;
		HashMap<String,User> userList = new HashMap<String,User>();
		for(File file: new File(usersDir).listFiles()){
			temp = new User(file);
	        userList.put(temp.getUsername(), temp);
		}
		return userList;
	}
	
	
	public void post(String post){
		User user = getUser(username);
		user.post(post);
		user.toFile(usersDir);
	}
	
	public ArrayList<Post> getFriendsPosts(){
		ArrayList<Post> posts = new ArrayList<Post>();
		HashMap<String,User> users = readUsers();
		User temp;
		for(String friendname:users.get(username).getFriends()) {
			temp = users.get(friendname);
			if(temp == null) {
				System.out.println("Error: invalid friend");
				continue;
			}
			for(Post post:temp.getPosts()) {
				posts.add(post);
			}
		}
		Collections.sort(posts);
		return posts;
	}
	
	public User getUser(String user) {
		return readUsers().get(user);
	}
	
	public boolean setUser(String username, String password) {
		this.username = username;
		User temp = getUser(username);
		if(temp == null) {
			return false;
		}
		if(temp.isPassword(password)) {
			return true;
		}else {
			temp = null;
		}
		return false;
	}
	
	public ArrayList<User> search(String sub){
		ArrayList<User>matches = new ArrayList<User>();
		HashMap<String,User> users = readUsers();
		for(String username: users.keySet()) {
			if((getUser(username)).getName().contains(sub)) {
					matches.add(getUser(username));
			}
		}
		return matches;
	}
	
	public void register(String username, String password, String name, String imageLocation){
		new User(username,password,name,imageLocation).toFile(usersDir);;
	}
	
	public void addFriend(String friendName) {
		User user = getUser(username);
		user.addFriend(friendName);
		user.toFile(usersDir);
	}
}
